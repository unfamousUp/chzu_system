package com.chzu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizations {

    @TableField("org_id")
    @ApiModelProperty("机构ID")
    private Integer orgId;

    @TableField("org_name")
    @ApiModelProperty("机构名称")
    private String orgName;

    @TableField("org_domain_name")
    @ApiModelProperty("机构网站域名")
    private String orgDomainName;

    @TableField("org_type")
    @ApiModelProperty("机构类型")
    private String orgType;
}
