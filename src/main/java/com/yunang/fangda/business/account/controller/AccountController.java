package com.yunang.fangda.business.account.controller;

import com.yunang.fangda.business.account.model.AccountModel;
import com.yunang.fangda.business.account.service.AccountService;
import com.yunang.fangda.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "账号接口", description = "账号接口")
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
    @ApiOperation(value = "账号分页查询")
//    @RequiresPermissions(value = "account-page")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<Page<AccountModel>> page(@ApiParam(value = "当前页数", required = true, example = "1为第一页")
                                                   @PathVariable("pageNow") int pageNow,
                                                   @ApiParam(value = "账号实体", required = true, example = "至少传递一个属性，值可以为\"\"")
                                                   @RequestBody AccountModel model) {
        return service.findAll(pageNow, pageSize, model);
    }

    @ApiOperation(value = "账号新增")
//    @RequiresPermissions(value = "account-save")
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseResult<AccountModel> save(@ApiParam(value = "账号实体", required = true, example = "根据业务填写必填项")
                                             @RequestBody AccountModel model) {
        return service.save(model);
    }

    /**
     * 根据id删除
     *
     * @param uuid
     * @return
     */
    @ApiOperation(value = "根据账号主键删除")
//    @RequiresRoles(value = {"admin"})
//    @RequiresPermissions(value = "account-delete")
    @RequestMapping(value = "/account/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<AccountModel> delete(@ApiParam(value = "账号主键", required = true, example = "后台获取的主键")
                                               @PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

}
