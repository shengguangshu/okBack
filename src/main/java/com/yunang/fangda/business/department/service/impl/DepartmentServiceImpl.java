package com.yunang.fangda.business.department.service.impl;

import com.yunang.fangda.business.department.jpa.DepartmentJpa;
import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.business.department.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentJpa jpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<DepartmentModel> save(DepartmentModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<DepartmentModel> delete(String uuid) {
        Optional<DepartmentModel> optional = jpa.findById(uuid);
        if (optional.isPresent()) {
            if (optional.get().getPositionModels().size() > 0) {
                return new ResponseResult<>(false, "请先删除下级职位");
            }
            jpa.deleteById(uuid);
            return new ResponseResult<>(true, "成功");
        }
        return new ResponseResult<>(false, "当前数据已不存在,请刷新后重试");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<DepartmentModel> update(DepartmentModel model) {
        if (model.getUuid() == null || model.getUuid().isEmpty()) {
            return new ResponseResult<>(false, "缺失主键");
        }
        if (model.getVersion() == null) {
            return new ResponseResult<>(false, "版本信息缺失");
        }
        DepartmentModel one = jpa.getOne(model.getUuid());
        if (!one.getVersion().equals(model.getVersion())) {
            return new ResponseResult<>(false, "信息过于陈旧，请刷新信息后重试");
        }
        if (model.getDepName() != null && !model.getDepName().isEmpty()) {
            one.setDepName(model.getDepName());
        }
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<DepartmentModel> getOne(String uuid) {
        Optional<DepartmentModel> optional = jpa.findById(uuid);
        return new ResponseResult<>(optional.isPresent(), optional.isPresent() ? "成功" : "未查询到记录", optional.orElse(null));
    }

    @Override
    public ResponseResult<Page<DepartmentModel>> findAll(int pageNow, int pageSize, DepartmentModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "depName"));
        Specification<DepartmentModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow - 1, pageSize, Sort.by(orders));
        Page<DepartmentModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    //    查询条件
    private Specification<DepartmentModel> queryTj(DepartmentModel model) {
        return new Specification<DepartmentModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<DepartmentModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getDepName() != null && !model.getDepName().isEmpty()) {
                        Predicate p1 = cb.like(root.get("depName").as(String.class), "%" + model.getDepName() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
                int size = predicates.size();
                return cb.and(predicates.toArray(new Predicate[size]));
            }
        };
    }
}
