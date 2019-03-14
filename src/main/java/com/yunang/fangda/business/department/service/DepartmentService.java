package com.yunang.fangda.business.department.service;

import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.utils.ResponseResult;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface DepartmentService {
    ResponseResult<DepartmentModel> save(DepartmentModel model);

    ResponseResult<DepartmentModel> delete(String uuid);

    ResponseResult<DepartmentModel> update(DepartmentModel model);

    ResponseResult<DepartmentModel> getOne(String uuid);

    ResponseResult<List<DepartmentModel>> findAll();
}
