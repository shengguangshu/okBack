package com.yunang.fangda.business.carNumber.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yunang.fangda.business.carBrand.model.CarBrandModel;
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
@ApiModel(value = "车辆型号表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "car_number_table")
@Table(comment = "车辆型号表", appliesTo = "car_number_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarNumberModel implements Serializable {
    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    @ApiModelProperty(value = "型号名称", example = "最大32位字符串")
    @Column(name = "number_name")
    private String numberName;
    @ApiModelProperty(value = "品牌", dataType = "com.yunang.fangda.business.carBrand.model.CarBrandModel")
    @OneToOne(targetEntity = CarBrandModel.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "car_brand_id", referencedColumnName = "uuid")
    private CarBrandModel carBrandModel;
    @ApiModelProperty(value = "数据版本信息", example = "修改删除时必填，从后台获取")
    @Version
    private Long version;

}
