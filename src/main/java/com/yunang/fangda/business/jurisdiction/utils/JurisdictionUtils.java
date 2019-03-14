package com.yunang.fangda.business.jurisdiction.utils;

import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class JurisdictionUtils {

    public List<JurisdictionModel> getDatas() {
        List<JurisdictionModel> list = new ArrayList<>();
        list.add(new JurisdictionModel("1", "新增账号", "account-save", "0", 1));
        list.add(new JurisdictionModel("2", "删除账号", "account-delete", "0", 1));
        list.add(new JurisdictionModel("3", "修改账号", "account-update", "0", 1));
        list.add(new JurisdictionModel("4", "分页查询账号", "account-page", "0", 1));
        return list;
    }
}
