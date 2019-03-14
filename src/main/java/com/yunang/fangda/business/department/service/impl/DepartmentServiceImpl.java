package com.yunang.fangda.business.department.service.impl;

import com.yunang.fangda.business.department.jpa.DepartmentJpa;
import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.business.department.service.DepartmentService;
import com.yunang.fangda.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
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
        if (model.getDepParent() != null && !model.getDepParent().isEmpty()) {
            one.setDepParent(model.getDepParent());
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
    public ResponseResult<List<DepartmentModel>> findAll() {
        List<DepartmentModel> all = jpa.findAll();
        if (!all.isEmpty()) {
            List<DepartmentModel> list = dg(all);
            if (list.size() > 0) {
                return new ResponseResult<>(true, "成功");
            } else {
                return new ResponseResult<>(false, "未查询到记录");
            }
        }
        return new ResponseResult<>(false, "未查询到记录");
    }

    private static List<DepartmentModel> dg(List<DepartmentModel> list) {
        List<DepartmentModel> all = new ArrayList<>();
        list.forEach(k -> {
            if (k.getDepParent().equals("0")) {
                all.add(findChildren(k, list));
            }
        });
        return all;
    }

    private static DepartmentModel findChildren(DepartmentModel treeNode, List<DepartmentModel> treeNodes) {
        for (DepartmentModel it : treeNodes) {
            if (treeNode.getUuid().equals(it.getDepParent())) {
                treeNode.getModelList().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
