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
        list.add(new JurisdictionModel("31", "部门管理", "department-page", "3", 1, null));
        list.add(new JurisdictionModel("32", "部门新增", "department-save", "3", 1, null));
        list.add(new JurisdictionModel("33", "部门修改", "department-update", "3", 1, null));
        list.add(new JurisdictionModel("34", "部门删除", "department-delete", "3", 1, null));
        list.add(new JurisdictionModel("35", "部门查询单个", "department-one", "3", 1, null));


        list.add(new JurisdictionModel("4", "职位管理", "zwgl", "1", 0, null));
        list.add(new JurisdictionModel("5", "初始化权限", "cshqx", "1", 0, null));

        list.add(new JurisdictionModel("6", "员工管理", "0", "0", 0, null));
        list.add(new JurisdictionModel("7", "员工管理", "yggl", "6", 0, null));
        list.add(new JurisdictionModel("8", "员工新增", "ygadd", "7", 1, null));
        list.add(new JurisdictionModel("9", "员工修改", "ygupdate", "7", 1, null));
        list.add(new JurisdictionModel("10", "员工删除", "ygdelete", "7", 1, null));
        list.add(new JurisdictionModel("11", "员工分页", "account-page", "7", 1, null));


        list.add(new JurisdictionModel("12", "标签管理", "tags", "0", 0, null));

        list.add(new JurisdictionModel("13", "品牌管理", "brand", "0", 0, null));
        list.add(new JurisdictionModel("14", "型号管理", "number", "0", 0, null));
        return list;
    }
}
