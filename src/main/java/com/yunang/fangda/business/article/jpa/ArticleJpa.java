package com.yunang.fangda.business.article.jpa;

import com.yunang.fangda.business.article.model.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleJpa extends JpaSpecificationExecutor<ArticleModel>,
        JpaRepository<ArticleModel, String> {
    ArticleModel findByCarNumId(String carNumId);
}
