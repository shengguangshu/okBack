package com.yunang.fangda.business.tags.jpa;

import com.yunang.fangda.business.tags.model.TagsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name 账户
 * @table
 * @remarks
 */
public interface TagsJpa extends JpaSpecificationExecutor<TagsModel>,
        JpaRepository<TagsModel, String> {

}
