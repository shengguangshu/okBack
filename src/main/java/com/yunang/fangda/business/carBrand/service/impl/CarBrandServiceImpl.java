package com.yunang.fangda.business.carBrand.service.impl;

import com.yunang.fangda.business.carBrand.jpa.CarBrandJpa;
import com.yunang.fangda.business.carBrand.model.CarBrandModel;
import com.yunang.fangda.business.carBrand.service.CarBrandService;
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
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandJpa jpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<CarBrandModel> save(CarBrandModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<CarBrandModel> delete(String uuid) {
        if (uuid == null || uuid.isEmpty())
            return new ResponseResult<>(false, "删除条件不能为空", null);
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<CarBrandModel> update(CarBrandModel model) {
        CarBrandModel one = jpa.getOne(model.getUuid());
        if (one == null || one.getUuid() == null) {
            return new ResponseResult<>(false, "信息已不存在");
        }
        if (model.getBrandName() != null && !model.getBrandName().isEmpty()) {
            one.setBrandName(model.getBrandName());
        }
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<CarBrandModel> findById(String uuid) {
        Optional<CarBrandModel> optional = jpa.findById(uuid);
        if (optional.orElse(null) != null)
            return new ResponseResult<>(true, "成功", optional.get());
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<Page<CarBrandModel>> findAll(int pageNow, int pageSize, CarBrandModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "brandName"));
        Specification<CarBrandModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow - 1, pageSize, Sort.by(orders));
        Page<CarBrandModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    //    查询条件
    private Specification<CarBrandModel> queryTj(CarBrandModel model) {
        return new Specification<CarBrandModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<CarBrandModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getBrandName() != null && !model.getBrandName().isEmpty()) {
                        Predicate p1 = cb.like(root.get("brandName").as(String.class), "%" + model.getBrandName() + "%");
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
    public ResponseResult<List<CarBrandModel>> findList() {
        List<CarBrandModel> list = jpa.findAll();
        if (list != null && list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }
}
