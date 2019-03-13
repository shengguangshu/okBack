package com.yunang.fangda.business.account.controller;

import com.yunang.fangda.business.account.model.AccountModel;
import com.yunang.fangda.business.account.service.AccountService;
import com.yunang.fangda.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author ld
 * @name
 * @table
 * @remarks //        jsr-303验证 手动验证版
 * ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
 * Validator validator = vf.getValidator();
 * Set<ConstraintViolation<LoginModel>> set = validator.validate(model);
 * for (ConstraintViolation<LoginModel> constraintViolation : set) {
 * result.setSuccess(false);
 * result.setMessage(constraintViolation.getMessage());
 * return result;
 * }
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private AccountService service;

    /**
     * 分页
     *
     * @param pageNow
     * @param model
     * @return
     */
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<Page<AccountModel>> page(@PathVariable("pageNow") int pageNow,
                                                   @RequestBody AccountModel model) {
        return service.findAll(pageNow, pageSize, model);
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<AccountModel> save(@RequestBody AccountModel model) {
        return service.save(model);
    }

    /**
     * 根据id删除
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> delete(@PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

}
