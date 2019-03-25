package com.yunang.fangda.business.carNumber.service.impl;

import com.yunang.fangda.business.carBrand.jpa.CarBrandJpa;
import com.yunang.fangda.business.carBrand.model.CarBrandModel;
import com.yunang.fangda.business.carNumber.jpa.CarNumberJpa;
import com.yunang.fangda.business.carNumber.model.CarNumberModel;
import com.yunang.fangda.business.carNumber.service.CarNumberService;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class CarNumberServiceImpl implements CarNumberService {

    @Autowired
    private CarNumberJpa jpa;
    @Autowired
    private CarBrandJpa carBrandJpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<CarNumberModel> save(CarNumberModel model) {
        if (model.getCarBrandModel() == null || model.getCarBrandModel().getUuid() == null) {
            return new ResponseResult<>(false, "未选择品牌");
        }
        Optional<CarBrandModel> optional = carBrandJpa.findById(model.getCarBrandModel().getUuid());
        if (optional.orElse(null) == null) {
            return new ResponseResult<>(false, "所选择信息已失效");
        }
        model.setCarBrandModel(optional.get());
        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<CarNumberModel> delete(String uuid) {
        if (uuid == null || uuid.isEmpty())
            return new ResponseResult<>(false, "删除条件不能为空", null);
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<CarNumberModel> update(CarNumberModel model) {
        CarNumberModel one = jpa.getOne(model.getUuid());
        if (one == null || one.getUuid() == null) {
            return new ResponseResult<>(false, "信息已不存在");
        }
        if (model.getCarBrandModel() != null && model.getCarBrandModel().getUuid() != null) {
            Optional<CarBrandModel> optional = carBrandJpa.findById(model.getCarBrandModel().getUuid());
            if (optional.orElse(null) == null) {
                return new ResponseResult<>(false, "所选择信息已失效");
            }
            model.setCarBrandModel(optional.get());
        }
        if (model.getNumberName() != null && !model.getNumberName().isEmpty()) {
            one.setNumberName(model.getNumberName());
        }
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<CarNumberModel> findById(String uuid) {
        Optional<CarNumberModel> optional = jpa.findById(uuid);
        if (optional.orElse(null) != null)
            return new ResponseResult<>(true, "成功", optional.get());
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<Page<CarNumberModel>> findAll(int pageNow, int pageSize, CarNumberModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "numberName"));
        Specification<CarNumberModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow - 1, pageSize, Sort.by(orders));
        Page<CarNumberModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    //    查询条件
    private Specification<CarNumberModel> queryTj(CarNumberModel model) {
        return new Specification<CarNumberModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<CarNumberModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getNumberName() != null && !model.getNumberName().isEmpty()) {
                        Predicate p1 = cb.like(root.get("numberName").as(String.class), "%" + model.getNumberName() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
//                if (model.getDrnyStar() != null && !model.getDrnyStar().trim().equals("")) {
////                    时间   小于等于 导入日期 大于等于 导入日期
//                    try {
//                        Predicate p1 = cb.greaterThanOrEqualTo(root.get("impDate").as(Long.class),
//                                sdfmat.parse(model.getDrnyStar()).getTime());
//                        predicates.add(cb.and(p1));
//                    } catch (Exception e) {
//                        return null;
//                    }
//                }
//                if (model.getDrnyEnd() != null && !model.getDrnyEnd().trim().equals("")) {
////                    时间  小于等于 导入日期
//                    try {
//                        Predicate p1 = cb.lessThanOrEqualTo(root.get("impDate").as(Long.class),
//                                sdfmat.parse(model.getDrnyEnd()).getTime());
//                        predicates.add(cb.and(p1));
//                    } catch (Exception e) {
//                        return null;
//                    }
//                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Override
    public ResponseResult<List<CarNumberModel>> findList() {
        List<CarNumberModel> list = jpa.findAll();
        if (list != null && list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }
}
