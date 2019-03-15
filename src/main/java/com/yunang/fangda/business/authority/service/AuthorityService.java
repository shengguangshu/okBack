package com.yunang.fangda.business.authority.service;

import com.yunang.fangda.business.authority.model.AuthorityModel;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.utils.ResponseResult;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
public interface AuthorityService {

    /**
     * 设置权限
     *
     * @param model
     * @return
     */
    ResponseResult<AuthorityModel> setPostIdAndJurId(AuthorityModel model);

    /**
     * 根据职位id获取权限
     *
     * @param autPosId
     * @return
     */
    ResponseResult<List<JurisdictionModel>> findByAutPosId(String autPosId);
}
