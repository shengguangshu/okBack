package com.yunang.fangda.business.tags.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yunang.fangda.business.account.model.AccountModel;
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
 * @author: LD
 * @date:
 * @description:
 */
@ApiModel(value = "标签表")
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tags_table")
@Table(comment = "标签表", appliesTo = "tags_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsModel implements Serializable {

    @ApiModelProperty(value = "主键", example = "除新增外必填，由后台自动生成")
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @ApiModelProperty(value = "创建日期", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "sys_time", length = 32)
    private Timestamp sysTime;

    @ApiModelProperty(value = "创建人", dataType = "com.yunang.fangda.business.account.model.AccountModel")
    @OneToOne(targetEntity = AccountModel.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "accountModel_id", referencedColumnName = "uuid")
    private AccountModel accountModel;

    @ApiModelProperty(value = "内容", example = "<div>body</div>")
    @Column(name = "tags_body", length = 16383)
    private String tagsBody;

    @Version
    private Long version;

}
