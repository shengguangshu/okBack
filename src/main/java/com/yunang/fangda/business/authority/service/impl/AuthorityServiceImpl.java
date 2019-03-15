package com.yunang.fangda.business.authority.service.impl;

import com.yunang.fangda.business.authority.jpa.AuthorityJpa;
import com.yunang.fangda.business.authority.model.AuthorityModel;
import com.yunang.fangda.business.authority.service.AuthorityService;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityJpa jpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<AuthorityModel> setPostIdAndJurId(AuthorityModel model) {
        JurisdictionModel one = jpa.findByAutPostIdAndAutJurId(model.getAutPostId(), model.getAutJurId());
        if (one != null) {
            jpa.deleteById(one.getUuid());
        } else {
            jpa.save(model);
        }
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<JurisdictionModel>> findByAutPosId(String autPosId) {
        List<JurisdictionModel> list = jpa.findByAutPostId(autPosId);
        if (list.size() > 0) {
            return new ResponseResult<>(true, "成功", list);
        } else {
            return new ResponseResult<>(false, "未查询到记录");
        }
    }
}
