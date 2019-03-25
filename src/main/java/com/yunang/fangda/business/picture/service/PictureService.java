package com.yunang.fangda.business.picture.service;

import com.yunang.fangda.business.picture.model.PictureModel;
import com.yunang.fangda.utils.ResponseResult;

import java.util.List;

public interface PictureService {
    //查询所有车型图片
    ResponseResult<List<PictureModel>> findAll();
    //根据车型id查找图片
    ResponseResult<PictureModel> findByCarNumId(String id);
    //保存
    ResponseResult<PictureModel> save(PictureModel pictureModel);
    //根据主键删除
    ResponseResult<PictureModel> delete(String uuid);

}
