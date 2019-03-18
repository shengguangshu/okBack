package com.yunang.fangda.business.jurisdiction.controller;

import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionQueryModel;
import com.yunang.fangda.business.jurisdiction.service.JurisdictionService;
import com.yunang.fangda.business.jurisdiction.utils.JurisdictionUtils;
import com.yunang.fangda.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Api(value = "权限基础信息接口", description = "权限基础信息接口")
@Slf4j
@RestController
@RequestMapping("/jurisdiction")
public class JurisdictionController {

    @Autowired
    private JurisdictionService service;

    @ApiOperation(value = "权限重置接口")
//    @RequiresRoles(value = {"admin"})
//    @RequiresPermissions(value = {"jurisdiction-reset"})
    @RequestMapping(value = "/setJurs", method = RequestMethod.GET)
    public ResponseResult<String> setJurs() {
        return service.setJurs(new JurisdictionUtils().getDatas());
    }

    @ApiOperation(value = "获取所有的权限")
//    @RequiresRoles(value = {"admin"})
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionModel>> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "根据职位获取权限")
    @RequestMapping(value = "/findByPosId/{posId}", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionQueryModel>> findByPosId(@PathVariable("posId") String posId) {
        return service.findByPosId(posId);
    }
}
