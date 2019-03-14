package com.yunang.fangda.business.jurisdiction.service;

import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.utils.ResponseResult;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface JurisdictionService {
    /**
     * 重置所有权限
     *
     * @param list
     * @return
     */
    ResponseResult<String> setJurs(List<JurisdictionModel> list);

    /**
     * 获取所有的权限
     *
     * @return
     */
    ResponseResult<List<JurisdictionModel>> findAll();

}
