package com.yunang.fangda.business.carNumber.service;

import com.yunang.fangda.business.carNumber.model.CarNumberModel;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface CarNumberService {

    ResponseResult<CarNumberModel> save(CarNumberModel model);

    ResponseResult<CarNumberModel> delete(String uuid);

    ResponseResult<CarNumberModel> update(CarNumberModel model);

    ResponseResult<CarNumberModel> findById(String uuid);

    ResponseResult<Page<CarNumberModel>> findAll(int pageNow, int pageSize, CarNumberModel model);

    ResponseResult<List<CarNumberModel>> findList();

}
