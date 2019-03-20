package com.yunang.fangda.business.position.service.impl;

import com.yunang.fangda.business.department.jpa.DepartmentJpa;
import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.business.position.jpa.PositionJpa;
import com.yunang.fangda.business.position.model.PositionModel;
import com.yunang.fangda.business.position.service.PositionService;
import com.yunang.fangda.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
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
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionJpa jpa;
    @Autowired
    private DepartmentJpa departmentJpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PositionModel> save(PositionModel model) {
        Optional<DepartmentModel> optional = departmentJpa.findById(model.getDepartmentModel().getUuid());
        if (optional.isPresent()) {
            model.setDepartmentModel(optional.get());
            jpa.save(model);
            return new ResponseResult<>(true, "成功");
        } else {
            return new ResponseResult<>(false, "部门已不存在");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PositionModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PositionModel> update(PositionModel model) {
        if (model.getUuid() == null || model.getUuid().isEmpty()) {
            return new ResponseResult<>(false, "缺失主键");
        }
        if (model.getVersion() == null) {
            return new ResponseResult<>(false, "版本信息缺失");
        }
        PositionModel one = jpa.getOne(model.getUuid());
        if (!one.getVersion().equals(model.getVersion())) {
            return new ResponseResult<>(false, "信息过于陈旧，请刷新信息后重试");
        }
        if (model.getPosName() != null && !model.getPosName().isEmpty()) {
            one.setPosName(model.getPosName());
        }
        if (model.getDepartmentModel() != null && model.getDepartmentModel().getUuid() != null) {
            Optional<DepartmentModel> optional = departmentJpa.findById(model.getDepartmentModel().getUuid());
            if (optional.isPresent()) {
                one.setDepartmentModel(optional.get());
            } else {
                return new ResponseResult<>(false, "部门已不存在");
            }
        }
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<PositionModel> getOne(String uuid) {
        Optional<PositionModel> optional = jpa.findById(uuid);
        return new ResponseResult<>(optional.isPresent(), optional.isPresent() ? "成功" : "未查询到记录", optional.orElse(null));
    }

    @Override
    public ResponseResult<Page<PositionModel>> findAll(int pageNow, int pageSize, PositionModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "posName"));
        Specification<PositionModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow - 1, pageSize, Sort.by(orders));
        Page<PositionModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    //    查询条件
    private Specification<PositionModel> queryTj(PositionModel model) {
        return new Specification<PositionModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<PositionModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getPosName() != null && !model.getPosName().isEmpty()) {
                        Predicate p1 = cb.like(root.get("posName").as(String.class), "%" + model.getPosName() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
                int size = predicates.size();
                return cb.and(predicates.toArray(new Predicate[size]));
            }
        };
    }

    @Override
    public ResponseResult<List<PositionModel>> findByPosParent(String posParent) {
//        List<PositionModel> list = jpa.findByPosParent(posParent);
//        if (list.size() > 0) {
////            List<PositionModel> dg = dg(list, posParent);
////            if (dg.size() > 0) {
//            return new ResponseResult<>(true, "成功", list);
////            } else {
////                return new ResponseResult<>(false, "未查询到记录");
////            }
//        }
        return new ResponseResult<>(false, "未查询到记录");
    }
}
