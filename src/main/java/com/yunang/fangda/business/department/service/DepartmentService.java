package com.yunang.fangda.business.department.service;

import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.data.domain.Page;

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

    ResponseResult<Page<DepartmentModel>> findAll(int pageNow, int pageSize, DepartmentModel model);
}
