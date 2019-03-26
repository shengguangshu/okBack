package com.yunang.fangda.business.article.service.serviceImpl;

import com.yunang.fangda.business.article.jpa.ArticleJpa;
import com.yunang.fangda.business.article.model.ArticleModel;
import com.yunang.fangda.business.article.service.ArticleService;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleJpa jpa;

    @Override
    public ResponseResult<List<ArticleModel>> findAll() {
        List<ArticleModel> all = jpa.findAll();
        if (all.size()>0){
            return new ResponseResult<List<ArticleModel>>(true,"成功",all);
        }else {
            return new ResponseResult<>(false,"未查询到车型图片",null);
        }
    }

    @Override
    public ResponseResult<ArticleModel> findByCarNumId(String id) {
        ArticleModel byCarNumId = jpa.findByCarNumId(id);
        if (StringUtils.isEmpty(byCarNumId)&&byCarNumId==null){
            return new ResponseResult<>(false,"未找到车型图片",null);
        }else {
            return new ResponseResult<>(true,"成功",byCarNumId);
        }
    }

    @Override
    public ResponseResult<ArticleModel> save(ArticleModel articleModel) {
        if (StringUtils.isEmpty(articleModel.getCarNumId())||articleModel.getCarNumId()==null){
            return new ResponseResult<>(false,"车型不能为空",null);
        }else {
            ArticleModel save = jpa.save(articleModel);
            return new ResponseResult<>(true,"成功",save);
        }
    }

    @Override
    public ResponseResult<ArticleModel> delete(String uuid) {
        if (StringUtils.isEmpty(uuid)||uuid==null){
            return new ResponseResult<>(false,"删除错误",null);
        }else{
            jpa.deleteById(uuid);
            return new ResponseResult<>(true,"删除成功",null);
        }
    }
}
