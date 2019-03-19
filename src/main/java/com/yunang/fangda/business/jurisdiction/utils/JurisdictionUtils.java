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
        list.add(new JurisdictionModel("6", "员工管理", "0", "0", 0, null));
        list.add(new JurisdictionModel("7", "员工管理", "yggl", "6", 0, null));
        list.add(new JurisdictionModel("8", "员工新增", "ygadd", "7", 1, null));
        list.add(new JurisdictionModel("9", "员工修改", "ygupdate", "7", 1, null));
        list.add(new JurisdictionModel("10", "员工删除", "ygdelete", "7", 1, null));
        list.add(new JurisdictionModel("11", "员工分页", "account-page", "7", 1, null));
        return list;
    }
}
