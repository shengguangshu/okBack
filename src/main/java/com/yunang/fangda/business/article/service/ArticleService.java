package com.yunang.fangda.business.article.service;

import com.yunang.fangda.business.article.model.ArticleModel;
import com.yunang.fangda.utils.ResponseResult;

import java.util.List;

public interface ArticleService {
    //查询所有车型图片
    ResponseResult<List<ArticleModel>> findAll();
    //根据车型id查找图片
    ResponseResult<ArticleModel> findByCarNumId(String id);
    //保存
    ResponseResult<ArticleModel> save(ArticleModel pictureModel);
    //根据主键删除
    ResponseResult<ArticleModel> delete(String uuid);

}
