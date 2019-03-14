package com.yunang.fangda.business.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

@ApiModel(value = "个人资料")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_table")
@Table(comment = "个人资料表", appliesTo = "user_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel implements Serializable {
    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    @ApiModelProperty(value = "姓名", example = "张某")
    @Column(name = "name", length = 32)
    private String name;
    @ApiModelProperty(value = "性别", example = "1：男 2：女")
    @Column(name = "sex", length = 1)
    private Integer sex;
    @ApiModelProperty(value = "年龄", example = "20")
    @Column(name = "age", length = 3)
    private Integer age;
    @ApiModelProperty(value = "生日", example = "yyyy-MM-dd GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "birthday")
    private Date birthday;
    @ApiModelProperty(value = "数据版本信息", example = "修改删除时必填，从后台获取")
    @Version
    private long version;

}
