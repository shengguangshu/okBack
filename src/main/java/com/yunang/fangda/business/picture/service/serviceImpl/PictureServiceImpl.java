package com.yunang.fangda.business.picture.service.serviceImpl;

import com.yunang.fangda.business.picture.jpa.PictureJpa;
import com.yunang.fangda.business.picture.model.PictureModel;
import com.yunang.fangda.business.picture.service.PictureService;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureJpa jpa;
    @Override
    public ResponseResult<List<PictureModel>> findAll() {
        List<PictureModel> all = jpa.findAll();
        if (all.size()>0){
            return new ResponseResult<List<PictureModel>>(true,"成功",all);
        }else {
            return new ResponseResult<>(false,"未查询到车型图片",null);
        }
    }

    @Override
    public ResponseResult<PictureModel> findByCarNumId(String id) {
        PictureModel byCarNumId = jpa.findByCarNumId(id);
        if (StringUtils.isEmpty(byCarNumId)&&byCarNumId==null){
            return new ResponseResult<>(false,"未找到车型图片",null);
        }else {
            return new ResponseResult<>(true,"成功",byCarNumId);
        }
    }

    @Override
    public ResponseResult<PictureModel> save(PictureModel pictureModel) {
        if (StringUtils.isEmpty(pictureModel.getCarNumId())||pictureModel.getCarNumId()==null){
            return new ResponseResult<>(false,"车型不能为空",null);
        }else {
            PictureModel save = jpa.save(pictureModel);
            return new ResponseResult<>(true,"成功",save);
        }
    }

    @Override
    public ResponseResult<PictureModel> delete(String uuid) {
        if (StringUtils.isEmpty(uuid)||uuid==null){
            return new ResponseResult<>(false,"删除错误",null);
        }else{
            jpa.deleteById(uuid);
            return new ResponseResult<>(true,"删除成功",null);
        }
    }
}
