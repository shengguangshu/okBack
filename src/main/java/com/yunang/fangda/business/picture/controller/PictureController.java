package com.yunang.fangda.business.picture.controller;


import com.yunang.fangda.business.picture.model.PictureModel;
import com.yunang.fangda.business.picture.service.PictureService;
import com.yunang.fangda.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cxc
 * @name
 * @table
 * @remarks
 */
@Api(value = "车型图片", description = "车型图片")
@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @ApiOperation(value = "查询所有车型图片")
    @RequestMapping("/findAll")
    public ResponseResult<List<PictureModel>> findAll(){
        return pictureService.findAll();
    }

    @ApiOperation(value = "查询指定车型图片")
    @RequestMapping(value = "/findByCarNumId/{carNumId}",method = RequestMethod.GET)
    public ResponseResult<PictureModel> findByCarNumId(
            @ApiParam(value = "车型ID", required = true, example = "车型ID")
            @PathVariable("carNumId") String carNumId){
        return pictureService.findByCarNumId(carNumId);
    }

    @ApiOperation(value = "增加车型图片")
    @RequestMapping(value = "/picture",method = RequestMethod.POST)
    public ResponseResult<PictureModel> save(
            @ApiParam(value = "车型图片实体", required = true, example = "至少传递一个属性，值可以为\"\"")
            @RequestBody PictureModel pictureModel){
        return pictureService.save(pictureModel);
    }

    @ApiOperation(value = "查询指定车型图片")
    @RequestMapping(value = "/picture/{uuid}",method = RequestMethod.DELETE)
    public ResponseResult<PictureModel> delete(
            @ApiParam(value = "uuid", required = true, example = "uuid")
            @PathVariable("uuid") String uuid){
        return pictureService.delete(uuid);
    }
}
