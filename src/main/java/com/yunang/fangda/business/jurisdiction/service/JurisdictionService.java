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
     * 批量新增权限
     *
     * @param list
     * @return
     */
    ResponseResult<JurisdictionModel> saveAll(List<JurisdictionModel> list);

    /**
     * 删除所有权限
     *
     * @return
     */
    ResponseResult<JurisdictionModel> deleteAll();
}
