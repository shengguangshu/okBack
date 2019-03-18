package com.yunang.fangda.business.position.service;

import com.yunang.fangda.business.position.model.PositionModel;
import com.yunang.fangda.utils.ResponseResult;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface PositionService {
    ResponseResult<PositionModel> save(PositionModel model);

    ResponseResult<PositionModel> delete(String uuid);

    ResponseResult<PositionModel> update(PositionModel model);

    ResponseResult<PositionModel> getOne(String uuid);

    ResponseResult<List<PositionModel>> findAll();

    ResponseResult<List<PositionModel>> findByPosParent(String posParent);
}
