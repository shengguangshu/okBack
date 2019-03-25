package com.yunang.fangda.business.carBrand.service;

import com.yunang.fangda.business.carBrand.model.CarBrandModel;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface CarBrandService {

    ResponseResult<CarBrandModel> save(CarBrandModel model);

    ResponseResult<CarBrandModel> delete(String uuid);

    ResponseResult<CarBrandModel> update(CarBrandModel model);

    ResponseResult<CarBrandModel> findById(String uuid);

    ResponseResult<Page<CarBrandModel>> findAll(int pageNow, int pageSize, CarBrandModel model);

    ResponseResult<List<CarBrandModel>> findList();

}
