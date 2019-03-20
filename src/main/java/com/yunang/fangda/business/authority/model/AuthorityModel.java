package com.yunang.fangda.business.authority.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ApiModelProperty(value = "职位主键", example = "最大50位字符串")
    @Column(name = "aut_pos_id", length = 50)
    private String autPostId;

    @ApiModelProperty(value = "权限主键", example = "最大50位字符串")
    @Column(name = "aut_jur_id", length = 50)
    private String autJurId;

    @Version
    private Long version;

}
