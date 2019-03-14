package com.yunang.fangda.business.jurisdiction.service.impl;

import com.yunang.fangda.business.jurisdiction.jpa.JurisdictionJpa;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.business.jurisdiction.service.JurisdictionService;
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
public class JurisdictionServiceImpl implements JurisdictionService {

    @Autowired
    private JurisdictionJpa jpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<String> setJurs(List<JurisdictionModel> list) {
        jpa.deleteAll();
        jpa.saveAll(list);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<JurisdictionModel>> findAll() {
        List<JurisdictionModel> list = jpa.findAll();
        if (list.isEmpty()) {
            return new ResponseResult<>(false, "未查询到记录");
        } else {
            return new ResponseResult<>(true, "成功", list);
        }
    }
}
