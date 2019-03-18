package com.yunang.fangda.business.department.controller;

import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.business.department.service.DepartmentService;
import com.yunang.fangda.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
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
@Api(value = "部门接口", description = "部门接口")
@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @ApiOperation(value = "查询所有部门")
//    @RequiresPermissions(value = "department-all")
    @RequestMapping(value = "/department/all", method = RequestMethod.GET)
    public ResponseResult<List<DepartmentModel>> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "部门新增")
    @RequiresPermissions(value = "department-save")
    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public ResponseResult<DepartmentModel> save(@ApiParam(value = "部门实体", required = true, example = "根据业务填写必填项")
                                                @RequestBody DepartmentModel model) {
        return service.save(model);
    }

    /**
     * 根据id删除
     *
     * @param uuid
     * @return
     */
    @ApiOperation(value = "根据账号主键删除")
    @RequiresPermissions(value = "department-delete")
    @RequestMapping(value = "/department/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<DepartmentModel> delete(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                  @PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @ApiOperation(value = "根据账号主键修改")
    @RequiresPermissions(value = {"department-one", "department-update"}, logical = Logical.OR)
    @RequestMapping(value = "/department/{uuid}", method = RequestMethod.GET)
    public ResponseResult<DepartmentModel> getOne(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                  @PathVariable("uuid") String uuid) {
        return service.getOne(uuid);
    }

    @ApiOperation(value = "根据账号主键修改")
    @RequiresPermissions(value = "department-update")
    @RequestMapping(value = "/department/{uuid}", method = RequestMethod.PUT)
    public ResponseResult<DepartmentModel> update(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                  @PathVariable("uuid") String uuid,
                                                  @ApiParam(value = "实体", required = true)
                                                  @RequestBody DepartmentModel model) {
        model.setUuid(uuid);
        return service.update(model);
    }

}
