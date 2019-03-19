package com.yunang.fangda.business.jurisdiction.utils;

import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class JurisdictionUtils {

    public List<JurisdictionModel> getDatas() {
        List<JurisdictionModel> list = new ArrayList<>();
        list.add(new JurisdictionModel("1", "系统管理", "0", "0", 0, null));
        list.add(new JurisdictionModel("2", "权限管理", "qxgl", "1", 0, null));
        list.add(new JurisdictionModel("3", "部门管理", "bmgl", "1", 0, null));
        list.add(new JurisdictionModel("4", "职位管理", "zwgl", "1", 0, null));
        list.add(new JurisdictionModel("5", "初始化权限", "cshqx", "1", 0, null));
//        list.add(new JurisdictionModel("1", "账号管理", "account-zhgl", "0", 0));
//        list.add(new JurisdictionModel("2", "新增账号", "account-save", "1", 1));
//        list.add(new JurisdictionModel("3", "删除账号", "account-delete", "1", 1));
//        list.add(new JurisdictionModel("4", "修改账号", "account-update", "1", 1));
//        list.add(new JurisdictionModel("5", "分页查询账号", "account-page", "1", 1));
        return list;
    }
}
