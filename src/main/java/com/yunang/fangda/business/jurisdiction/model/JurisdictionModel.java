package com.yunang.fangda.business.jurisdiction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: LD
 * @date:
 * @description:
 */
@ApiModel(value = "账号表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "jurisdiction_table")
@Table(comment = "权限表", appliesTo = "jurisdiction_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurisdictionModel implements Serializable {

    @ApiModelProperty(value = "主键")
    @Id
    private String uuid;
    @ApiModelProperty(value = "权限名称")
    @Column(name = "jur_name", length = 32)
    private String jurName;
    @ApiModelProperty(value = "权限标识")
    @Column(name = "jur_flag", length = 32)
    private String jurFlag;
    @ApiModelProperty(value = "权限父级")
    @Column(name = "jur_parent", length = 32)
    private String jurParent;
    @ApiModelProperty(value = "权限类型",example = "0：菜单 1：按钮")
    @Column(name = "jur_type", length = 32)
    private Integer jurType;
}
