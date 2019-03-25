package com.yunang.fangda.business.jurisdiction.service.impl;

import com.yunang.fangda.business.authority.jpa.AuthorityJpa;
import com.yunang.fangda.business.jurisdiction.jpa.JurisdictionJpa;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionQueryModel;
import com.yunang.fangda.business.jurisdiction.service.JurisdictionService;
import com.yunang.fangda.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private AuthorityJpa authorityJpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<String> setJurs(List<JurisdictionModel> list) {
        authorityJpa.deleteAll();
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

    @Override
    public ResponseResult<List<JurisdictionQueryModel>> findByPosId(String posId) {
        List<Object[]> list = jpa.findByPosId(posId);
        if (list.size() > 0) {
            List<JurisdictionQueryModel> list1 = new ArrayList<>();
            list.forEach(k -> {
                JurisdictionQueryModel model = new JurisdictionQueryModel((String) k[0], (String) k[1], (String) k[2], (String) k[3], (Integer) k[4], null, (String) k[5]);
                list1.add(model);
            });
            return new ResponseResult<>(true, "成功", list1);
        }
        return new ResponseResult<>(false, "未查询到记录");
    }
}
