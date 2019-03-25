package com.yunang.fangda.business.carNumber.controller;

import com.yunang.fangda.business.carNumber.model.CarNumberModel;
import com.yunang.fangda.business.carNumber.service.CarNumberService;
import com.yunang.fangda.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Api(value = "车辆型号", description = "车辆型号")
@Slf4j
@RestController
@RequestMapping("/carnumber")
public class CarNumberController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private CarNumberService service;

    @ApiOperation(value = "查询所有分页")
//    @RequiresPermissions(value = "position-all")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<Page<CarNumberModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                        @RequestBody CarNumberModel model) {
        return service.findAll(pageNow, pageSize, model);
    }

    @ApiOperation(value = "查询所有")
//    @RequiresPermissions(value = "position-all")
    @RequestMapping(value = "/find/all", method = RequestMethod.GET)
    public ResponseResult<List<CarNumberModel>> findList() {
        return service.findList();
    }

    @ApiOperation(value = "新增")
//    @RequiresPermissions(value = "position-save")
    @RequestMapping(value = "/carnumber", method = RequestMethod.POST)
    public ResponseResult<CarNumberModel> save(@ApiParam(value = "实体", required = true, example = "根据业务填写必填项")
                                               @RequestBody CarNumberModel model) {
        return service.save(model);
    }

    /**
     * 根据id删除
     *
     * @param uuid
     * @return
     */
    @ApiOperation(value = "根据主键删除")
//    @RequiresPermissions(value = "position-delete")
    @RequestMapping(value = "/carnumber/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<CarNumberModel> delete(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                 @PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @ApiOperation(value = "根据主键查询")
//    @RequiresPermissions(value = {"position-one", "position-update"}, logical = Logical.OR)
    @RequestMapping(value = "/carnumber/{uuid}", method = RequestMethod.GET)
    public ResponseResult<CarNumberModel> getOne(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                 @PathVariable("uuid") String uuid) {
        return service.findById(uuid);
    }

    @ApiOperation(value = "根据主键修改")
//    @RequiresPermissions(value = "position-update")
    @RequestMapping(value = "/carnumber/{uuid}", method = RequestMethod.PUT)
    public ResponseResult<CarNumberModel> update(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                 @PathVariable("uuid") String uuid,
                                                 @ApiParam(value = "实体", required = true)
                                                 @RequestBody CarNumberModel model) {
        model.setUuid(uuid);
        return service.update(model);
    }

}
