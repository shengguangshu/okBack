package com.yunang.fangda.business.position.service;

import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.business.position.model.PositionModel;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.data.domain.Page;

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

    ResponseResult<Page<PositionModel>> findAll(int pageNow, int pageSize, PositionModel model);

    ResponseResult<List<PositionModel>> findAll2();

    ResponseResult<List<PositionModel>> findByPosParent(String posParent);
}
