package com.yunang.fangda.business.picture.jpa;

import com.yunang.fangda.business.picture.model.PictureModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PictureJpa extends JpaSpecificationExecutor<PictureModel>,
        JpaRepository<PictureModel, String> {
    PictureModel findByCarNumId(String carNumId);
}
