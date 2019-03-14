package com.yunang.fangda.business.department.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: LD
 * @date:
 * @description:
 */
@ApiModel(value = "部门表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "depardtment_table")
@Table(comment = "部门表", appliesTo = "depardtment_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentModel implements Serializable {

    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @ApiModelProperty(value = "部门名称", example = "最大32位字符串")
    @Column(name = "dep_name", length = 32)
    private String depName;

    @ApiModelProperty(value = "部门父级", example = "最大32位字符串")
    @Column(name = "dep_parent", length = 32)
    private String depParent;

    @Transient
    private List<DepartmentModel> modelList = new ArrayList<>();

    @Version
    private Long version;

}
