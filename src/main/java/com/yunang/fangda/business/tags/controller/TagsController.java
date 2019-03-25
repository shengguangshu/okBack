package com.yunang.fangda.business.tags.controller;

import com.yunang.fangda.business.account.model.AccountModel;
import com.yunang.fangda.business.tags.model.TagsModel;
import com.yunang.fangda.business.tags.service.TagsService;
import com.yunang.fangda.sys.shiro.JWTUtils;
import com.yunang.fangda.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
@Api(value = "标签接口", description = "标签接口")
@Slf4j
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private TagsService service;

    /**
     * 分页
     *
     * @param pageNow
     * @param model
     * @return
     */
    @ApiOperation(value = "分页查询")
//    @RequiresPermissions(value = "account-page")
    @RequestMapping(value = "/page/{pageNow}", method = RequestMethod.POST)
    public ResponseResult<Page<TagsModel>> page(@ApiParam(value = "当前页数", required = true, example = "1为第一页")
                                                @PathVariable("pageNow") int pageNow,
                                                @ApiParam(value = "实体", required = true, example = "至少传递一个属性")
                                                @RequestBody TagsModel model) {
        return service.findAll(pageNow, pageSize, model);
    }

    @ApiOperation(value = "新增")
//    @RequiresPermissions(value = "account-save")
    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public ResponseResult<TagsModel> save(@ApiParam(value = "实体", required = true, example = "根据业务填写必填项")
                                          @RequestBody TagsModel model,
                                          HttpServletRequest request) {
        String accId = JWTUtils.getAccId(request);
//        因为前台没有传递此，可能会为null
//        model.setAccountModel(new AccountModel());
//        model.getAccountModel().setUuid(accId);
        return service.save(model);
    }

    /**
     * 根据id删除
     *
     * @param uuid
     * @return
     */
    @ApiOperation(value = "根据主键删除")
//    @RequiresRoles(value = {"admin"})
//    @RequiresPermissions(value = "account-delete")
    @RequestMapping(value = "/tags/{uuid}", method = RequestMethod.DELETE)
    public ResponseResult<TagsModel> delete(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                            @PathVariable("uuid") String uuid) {
        return service.delete(uuid);
    }

    @ApiOperation(value = "修改")
//    @RequiresPermissions(value = "account-save")
    @RequestMapping(value = "/tags/{uuid}", method = RequestMethod.PUT)
    public ResponseResult<TagsModel> update(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                            @PathVariable("uuid") String uuid,
                                            @ApiParam(value = "实体", required = true, example = "根据业务填写必填项")
                                            @RequestBody TagsModel model) {
        model.setUuid(uuid);
        return service.update(model);
    }

    /**
     * 根据id查询
     *
     * @param uuid
     * @return
     */
    @ApiOperation(value = "根据主键查询")
//    @RequiresRoles(value = {"admin"})
//    @RequiresPermissions(value = "account-delete")
    @RequestMapping(value = "/tags/{uuid}", method = RequestMethod.GET)
    public ResponseResult<TagsModel> getOne(@ApiParam(value = "主键", required = true, example = "后台获取的主键")
                                            @PathVariable("uuid") String uuid) {
        return service.findById(uuid);
    }

}
