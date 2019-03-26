package com.yunang.fangda.business.article.controller;


import com.yunang.fangda.business.article.model.ArticleModel;
import com.yunang.fangda.business.article.service.ArticleService;
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
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "查询所有车型图片")
    @RequestMapping("/findAll")
    public ResponseResult<List<ArticleModel>> findAll(){
        return articleService.findAll();
    }

    @ApiOperation(value = "查询指定车型图片")
    @RequestMapping(value = "/findByCarNumId/{carNumId}",method = RequestMethod.GET)
    public ResponseResult<ArticleModel> findByCarNumId(
            @ApiParam(value = "车型ID", required = true, example = "车型ID")
            @PathVariable("carNumId") String carNumId){
        return articleService.findByCarNumId(carNumId);
    }

    @ApiOperation(value = "增加车型图片")
    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public ResponseResult<ArticleModel> save(
            @ApiParam(value = "车型图片实体", required = true, example = "至少传递一个属性，值可以为\"\"")
            @RequestBody ArticleModel articleModel){
        return articleService.save(articleModel);
    }

    @ApiOperation(value = "查询指定车型图片")
    @RequestMapping(value = "/article/{uuid}",method = RequestMethod.DELETE)
    public ResponseResult<ArticleModel> delete(
            @ApiParam(value = "uuid", required = true, example = "uuid")
            @PathVariable("uuid") String uuid){
        return articleService.delete(uuid);
    }
}
