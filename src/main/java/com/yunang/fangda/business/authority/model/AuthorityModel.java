package com.yunang.fangda.business.authority.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yunang.fangda.business.jurisdiction.model.JurisdictionModel;
import com.yunang.fangda.business.position.model.PositionModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: LD
 * @date:
 * @description:
 */
@ApiModel(value = "职位权限对应表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "authority_table")
@Table(comment = "职位权限对应表", appliesTo = "authority_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorityModel implements Serializable {

    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @ApiModelProperty(value = "职位", dataType = "com.yunang.fangda.business.position.model.PositionModel")
    @OneToOne(targetEntity = PositionModel.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "position_id", referencedColumnName = "uuid")
    private PositionModel positionModel;

    @ApiModelProperty(value = "权限", dataType = "com.yunang.fangda.business.jurisdiction.model.JurisdictionModel")
    @OneToOne(targetEntity = JurisdictionModel.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "jurisdiction_id", referencedColumnName = "uuid")
    private JurisdictionModel jurisdictionModel;

    @Version
    private Long version;

}
