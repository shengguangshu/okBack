package com.yunang.fangda.business.position.service.impl;

import com.yunang.fangda.business.position.jpa.PositionJpa;
import com.yunang.fangda.business.position.model.PositionModel;
import com.yunang.fangda.business.position.service.PositionService;
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
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionJpa jpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<PositionModel> save(PositionModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
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
        if (model.getPosParent() != null && !model.getPosParent().isEmpty()) {
            one.setPosParent(model.getPosParent());
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
    public ResponseResult<List<PositionModel>> findAll() {
        List<PositionModel> all = jpa.findAll();
        if (!all.isEmpty()) {
            List<PositionModel> list = dg(all);
            if (list.size() > 0) {
                return new ResponseResult<>(true, "成功");
            } else {
                return new ResponseResult<>(false, "未查询到记录");
            }
        }
        return new ResponseResult<>(false, "未查询到记录");
    }

    private static List<PositionModel> dg(List<PositionModel> list) {
        List<PositionModel> all = new ArrayList<>();
        list.forEach(k -> {
            if (k.getPosParent().equals("0")) {
                all.add(findChildren(k, list));
            }
        });
        return all;
    }

    private static PositionModel findChildren(PositionModel treeNode, List<PositionModel> treeNodes) {
        for (PositionModel it : treeNodes) {
            if (treeNode.getUuid().equals(it.getPosParent())) {
                treeNode.getModelList().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
