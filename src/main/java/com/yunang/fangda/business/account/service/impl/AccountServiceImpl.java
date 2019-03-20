package com.yunang.fangda.business.account.service.impl;

import com.yunang.fangda.business.account.jpa.AccountJpa;
import com.yunang.fangda.business.account.model.AccountModel;
import com.yunang.fangda.business.account.service.AccountService;
import com.yunang.fangda.business.department.jpa.DepartmentJpa;
import com.yunang.fangda.business.department.model.DepartmentModel;
import com.yunang.fangda.business.position.jpa.PositionJpa;
import com.yunang.fangda.business.position.model.PositionModel;
import com.yunang.fangda.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.nio.charset.StandardCharsets;
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountJpa jpa;
    @Autowired
    private DepartmentJpa departmentJpa;
    @Autowired
    private PositionJpa positionJpa;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<AccountModel> save(AccountModel model) {
        List<AccountModel> list = jpa.findByAccount(model.getAccount());
        if (list.size() > 0) {
            return new ResponseResult<>(false, "该账户已存在", null);
        } else {
            if (model.getDepartmentModel() == null || model.getDepartmentModel().getUuid() == null) {
                return new ResponseResult<>(false, "缺失部门");
            }
            Optional<DepartmentModel> optional = departmentJpa.findById(model.getDepartmentModel().getUuid());
            if (!optional.isPresent()) {
                return new ResponseResult<>(false, "部门已不存在");
            }
            model.setDepartmentModel(optional.get());
            if (model.getPositionModel() == null || model.getPositionModel().getUuid() == null) {
                return new ResponseResult<>(false, "缺失职位");
            }
            Optional<PositionModel> optional2 = positionJpa.findById(model.getPositionModel().getUuid());
            if (!optional2.isPresent()) {
                return new ResponseResult<>(false, "职位已不存在");
            }
            model.setPositionModel(optional2.get());
            model.setIsLogin(1);
            model.setSystemTimes(new Timestamp(System.currentTimeMillis()));
//            密码加密
            String md5Password = DigestUtils.md5DigestAsHex(model.getPassword().getBytes(StandardCharsets.UTF_8));
            model.setPassword(md5Password);
            jpa.save(model);
            return new ResponseResult<>(true, "成功", null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<AccountModel> delete(String uuid) {
        if (uuid == null || uuid.isEmpty())
            return new ResponseResult<>(false, "删除条件不能为空", null);
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功", null);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<AccountModel> update(AccountModel model) {
        AccountModel one = jpa.getOne(model.getUuid());
        if (one == null || one.getUuid() == null) {
            return new ResponseResult<>(false, "该账号已不存在");
        }
        if (model.getDepartmentModel() != null && model.getDepartmentModel().getUuid() != null) {
            Optional<DepartmentModel> optional = departmentJpa.findById(model.getDepartmentModel().getUuid());
            if (!optional.isPresent()) {
                return new ResponseResult<>(false, "部门已不存在");
            }
            one.setDepartmentModel(optional.get());
        }

        if (model.getPositionModel() != null && model.getPositionModel().getUuid() != null) {
            Optional<PositionModel> optional2 = positionJpa.findById(model.getPositionModel().getUuid());
            if (!optional2.isPresent()) {
                return new ResponseResult<>(false, "职位已不存在");
            }
            one.setPositionModel(optional2.get());
        }
        if (model.getIsLogin() > 0) {
            one.setIsLogin(model.getIsLogin());
        }
        jpa.flush();
        return new ResponseResult<>(true, "成功", null);
    }

    @Override
    public ResponseResult<AccountModel> findById(String uuid) {
        AccountModel one = jpa.getOne(uuid);
        return new ResponseResult<>(true, "成功", one);
    }

    @Override
    public ResponseResult<AccountModel> findByAccount(String account) {
        List<AccountModel> list = jpa.findByAccount(account);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list.get(0));
        else
            return new ResponseResult<>(false, "记录未找到", null);
    }

    @Override
    public ResponseResult<Page<AccountModel>> findAll(int pageNow, int pageSize, AccountModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "systemTimes"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "account"));
        Specification<AccountModel> spec = queryTj(model);
        PageRequest pageRequest = PageRequest.of(pageNow - 1, pageSize, Sort.by(orders));
        Page<AccountModel> page = jpa.findAll(spec, pageRequest);
        if (!page.getContent().isEmpty())
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }

    //    查询条件
    private Specification<AccountModel> queryTj(AccountModel model) {
        return new Specification<AccountModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<AccountModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getAccount() != null && !model.getAccount().isEmpty()) {
                        Predicate p1 = cb.like(root.get("account").as(String.class), "%" + model.getAccount() + "%");
                        predicates.add(cb.and(p1));
                    }
                    if (model.getUser() != null){
                        if (model.getUser().getName() != null && !model.getUser().getName().isEmpty()){
                            Predicate p1 = cb.like(root.get("user").get("name").as(String.class), "%" + model.getUser().getName() + "%");
                            predicates.add(cb.and(p1));
                        }
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

    @Override
    public ResponseResult<List<AccountModel>> findByAccountLike(String account) {
        List<AccountModel> list = jpa.findByAccountLike("%" + account + "%");
        if (list != null && list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据", null);
    }
}
