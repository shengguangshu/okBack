package com.yunang.fangda.business.picture.model;

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

@ApiModel(value = "车型图片")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "picture_table")
@Table(comment = "车辆型号表", appliesTo = "picture_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PictureModel {
    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    @ApiModelProperty(value = "图片名称", example = "最大32位字符串")
    @Column(name = "picture_name")
    private String pictureName;
    @ApiModelProperty(value = "车型id", example = "最大32位字符串")
    @Column(name = "car_num_id")
    private String carNumId;
}
