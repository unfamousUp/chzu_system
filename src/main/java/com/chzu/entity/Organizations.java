package com.chzu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizations {

    @TableId(value = "org_id", type = IdType.AUTO)
    @ApiModelProperty("机构ID")
    private Integer orgId;

    @TableField("org_name")
    @ApiModelProperty("机构名称")
    @ExcelProperty("网站名称")
    private String orgName;

    @TableField("org_domain_name")
    @ApiModelProperty("机构网站域名")
    @ExcelProperty("域名")
    private String orgDomainName;

    @TableField("org_type")
    @ApiModelProperty("机构类型")
    @ExcelProperty("类型")
    private String orgType;
}
