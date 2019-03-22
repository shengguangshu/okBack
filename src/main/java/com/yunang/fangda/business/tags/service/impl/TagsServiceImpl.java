package com.yunang.fangda.business.tags.service.impl;

import com.yunang.fangda.business.account.jpa.AccountJpa;
import com.yunang.fangda.business.account.model.AccountModel;
import com.yunang.fangda.business.tags.jpa.TagsJpa;
import com.yunang.fangda.business.tags.model.TagsModel;
import com.yunang.fangda.business.tags.service.TagsService;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsJpa jpa;
    @Autowired
    private AccountJpa accountJpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<TagsModel> save(TagsModel model) {
        Optional<AccountModel> optional = accountJpa.findById(model.getAccountModel().getUuid());
        if (optional.orElse(null) == null) {
            return new ResponseResult<>(false, "缺失创建人");
        }
        model.setAccountModel(optional.get());
        model.setSysTime(new Timestamp(System.currentTimeMillis()));
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<TagsModel> delete(String uuid) {
        if (uuid == null || uuid.isEmpty())
            return new ResponseResult<>(false, "删除条件不能为空", null);
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<TagsModel> update(TagsModel model) {
        TagsModel one = jpa.getOne(model.getUuid());
        if (one == null || one.getUuid() == null) {
            return new ResponseResult<>(false, "该账号已不存在");
        }
        if (model.getTagsBody() != null) {
            one.setTagsBody(model.getTagsBody());
        }
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<TagsModel> findById(String uuid) {
        Optional<TagsModel> optional = jpa.findById(uuid);
        if (optional.orElse(null) != null)
            return new ResponseResult<>(true, "成功", optional.get());
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<Page<TagsModel>> findAll(int pageNow, int pageSize, TagsModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "systemTimes"));
        Specification<TagsModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow - 1, pageSize, Sort.by(orders));
        Page<TagsModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    //    查询条件
    private Specification<TagsModel> queryTj(TagsModel model) {
        return new Specification<TagsModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<TagsModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getTagsBody() != null && !model.getTagsBody().isEmpty()) {
                        Predicate p1 = cb.like(root.get("tags_body").as(String.class), "%" + model.getTagsBody() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
//                if (model.getDrnyStar() != null && !model.getDrnyStar().trim().equals("")) {
////                    时间   小于等于 导入日期 大于等于 导入日期
//                    try {
//                        Predicate p1 = cb.greaterThanOrEqualTo(root.get("impDate").as(Long.class),
//                                sdfmat.parse(model.getDrnyStar()).getTime());
//                        predicates.add(cb.and(p1));
//                    } catch (Exception e) {
//                        return null;
//                    }
//                }
//                if (model.getDrnyEnd() != null && !model.getDrnyEnd().trim().equals("")) {
////                    时间  小于等于 导入日期
//                    try {
//                        Predicate p1 = cb.lessThanOrEqualTo(root.get("impDate").as(Long.class),
//                                sdfmat.parse(model.getDrnyEnd()).getTime());
//                        predicates.add(cb.and(p1));
//                    } catch (Exception e) {
//                        return null;
//                    }
//                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

}
