package com.yunang.fangda.business.authority.controller;

import com.yunang.fangda.business.authority.model.AuthorityModel;
import com.yunang.fangda.business.authority.service.AuthorityService;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Api(value = "职位权限对应接口", description = "职位权限对应接口")
@Slf4j
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService service;

    @ApiOperation(value = "根据职位id查询权限")
    @RequiresPermissions(value = "authority-findByAutPosId")
    @RequestMapping(value = "/authority/{autPostId}", method = RequestMethod.GET)
    public ResponseResult<List<JurisdictionModel>> findByAutPosId(@PathVariable("autPostId") String autPostId) {
        return service.findByAutPosId(autPostId);
    }

    @ApiOperation(value = "设置职位权限")
    @RequiresPermissions(value = "authority-setPostIdAndJurId")
    @RequestMapping(value = "/authority", method = RequestMethod.POST)
    public ResponseResult<AuthorityModel> setPostIdAndJurId(@ApiParam(value = "实体", required = true, example = "根据业务填写必填项")
                                                            @RequestBody AuthorityModel model) {
        return service.setPostIdAndJurId(model);
    }

}
