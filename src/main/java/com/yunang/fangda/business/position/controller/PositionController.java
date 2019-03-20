package com.yunang.fangda.business.position.controller;

import com.yunang.fangda.business.position.model.PositionModel;
import com.yunang.fangda.business.position.service.PositionService;
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
@Api(value = "职位接口", description = "职位接口")
@Slf4j
@RestController
@RequestMapping("/position")
public class PositionController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private PositionService service;

    @ApiOperation(value = "查询所有分页")
//    @RequiresPermissions(value = "position-all")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<Page<PositionModel>> findAll(@PathVariable("pageNow") int pageNow,
                                                       @RequestBody PositionModel model) {
        return service.findAll(pageNow, pageSize, model);
    }

    @ApiOperation(value = "查询所有")
//    @RequiresPermissions(value = "position-all")
    @RequestMapping(value = "/find/all", method = RequestMethod.GET)
    public ResponseResult<List<PositionModel>> findAll2() {
        return service.findAll2();
    }

    @ApiOperation(value = "根据部门id查询")
//    @RequiresPermissions(value = "position-byDepId")
    @RequestMapping(value = "/byDepId/{depId}", method = RequestMethod.GET)
    public ResponseResult<List<PositionModel>> byDepId(@PathVariable("depId") String depId) {
        return service.findByPosParent(depId);
    }

    @ApiOperation(value = "新增")
//    @RequiresPermissions(value = "position-save")
    @RequestMapping(value = "/position", method = RequestMethod.POST)
    public ResponseResult<PositionModel> save(@ApiParam(value = "实体", required = true, example = "根据业务填写必填项")
                                              @RequestBody PositionModel model) {
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
    @RequestMapping(value = "/position/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<PositionModel> delete(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                @PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @ApiOperation(value = "根据主键查询")
//    @RequiresPermissions(value = {"position-one", "position-update"}, logical = Logical.OR)
    @RequestMapping(value = "/position/{uuid}", method = RequestMethod.GET)
    public ResponseResult<PositionModel> getOne(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                @PathVariable("uuid") String uuid) {
        return service.getOne(uuid);
    }

    @ApiOperation(value = "根据主键修改")
//    @RequiresPermissions(value = "position-update")
    @RequestMapping(value = "/position/{uuid}", method = RequestMethod.PUT)
    public ResponseResult<PositionModel> update(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                                @PathVariable("uuid") String uuid,
                                                @ApiParam(value = "实体", required = true)
                                                @RequestBody PositionModel model) {
        model.setUuid(uuid);
        return service.update(model);
    }

}
