package com.yunang.fangda.business.carBrand.model;

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
 * @author ld
 * @name
 * @table
 * @remarks
 */
@ApiModel(value = "车辆品牌表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "car_brand_table")
@Table(comment = "车辆品牌表", appliesTo = "car_brand_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarBrandModel implements Serializable {
    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    @ApiModelProperty(value = "品牌名称", example = "最大32位字符串")
    @Column(name = "brand_name")
    private String brandName;
    @ApiModelProperty(value = "数据版本信息", example = "修改删除时必填，从后台获取")
    @Version
    private Long version;

}
