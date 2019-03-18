package com.yunang.fangda.business.jurisdiction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
@ApiModel(value = "账号表")
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurisdictionQueryModel extends JurisdictionModel implements Serializable {

    private String isy;

    public String getIsy() {
        return isy;
    }

    public void setIsy(String isy) {
        this.isy = isy;
    }

    public JurisdictionQueryModel() {
        super();
    }

    public JurisdictionQueryModel(String uuid, String jurName, String jurFlag, String jurParent, Integer jurType, List<JurisdictionModel> list, String isy) {
        super(uuid, jurName, jurFlag, jurParent, jurType, list);
        this.isy = isy;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
