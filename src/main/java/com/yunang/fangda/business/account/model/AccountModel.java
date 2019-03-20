package com.yunang.fangda.business.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yunang.fangda.business.department.model.DepartmentModel;
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
import java.sql.Timestamp;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@ApiModel(value = "账号表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account_table")
@Table(comment = "账户表", appliesTo = "account_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountModel implements Serializable {
    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    @ApiModelProperty(value = "账号", example = "最大32位字符串")
    @Column(name = "account", length = 32)
    private String account;
    @ApiModelProperty(value = "密码", example = "最大32位字符串")
    @Column(name = "password", length = 32)
    private String password;
    @ApiModelProperty(value = "生成时间", example = "yyyy-MM-dd HH:mm:ss GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "system_times", columnDefinition = "TIMESTAMP")
    private Timestamp systemTimes;
    @ApiModelProperty(value = "是否允许登陆", example = "1:允许；2:不允许")
    @Column(name = "is_login", length = 1)
    private Integer isLogin;
    @ApiModelProperty(value = "个人资料", dataType = "com.yunang.fangda.business.account.model.UserModel")
    @OneToOne(targetEntity = UserModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "uuid")
    private UserModel user;
    @ApiModelProperty(value = "部门", dataType = "com.yunang.fangda.business.department.model.DepartmentModel")
    @OneToOne(targetEntity = DepartmentModel.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "department_id", referencedColumnName = "uuid")
    private DepartmentModel departmentModel;
    @ApiModelProperty(value = "职位", dataType = "com.yunang.fangda.business.position.model.PositionModel")
    @OneToOne(targetEntity = PositionModel.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "position_id", referencedColumnName = "uuid")
    private PositionModel positionModel;


    @ApiModelProperty(value = "数据版本信息", example = "修改删除时必填，从后台获取")
    @Version
    private Long version;

}
