package com.yunang.fangda.business.tags.service;

import com.yunang.fangda.business.tags.model.TagsModel;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.data.domain.Page;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface TagsService {

    ResponseResult<TagsModel> save(TagsModel model);

    ResponseResult<TagsModel> delete(String uuid);

    ResponseResult<TagsModel> update(TagsModel model);

    ResponseResult<TagsModel> findById(String uuid);

    ResponseResult<Page<TagsModel>> findAll(int pageNow, int pageSize, TagsModel model);

}
