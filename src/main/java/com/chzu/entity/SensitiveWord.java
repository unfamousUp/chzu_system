package com.chzu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sensitive_word_monitor")
public class SensitiveWord {

    @TableId("id")
    @ApiModelProperty("敏感词Id")
    private Integer id;

    @TableField("domain_name")
    @ApiModelProperty("域名")
    @ExcelProperty("域名")
    private String domainName;

    @TableField("sensitive_word")
    @ApiModelProperty("敏感词")
    @ExcelProperty("敏感词")
    private String sensitiveWord;

    @TableField("title")
    @ApiModelProperty("文章标题")
    @ExcelProperty("文章标题")
    private String title;

    @TableField("suggestion")
    @ApiModelProperty("修改建议")
    @ExcelProperty("修改建议")
    private String suggestion;

    @TableField("location")
    @ApiModelProperty("所在片段")
    @ExcelProperty("所在片段")
    private String location;

    @TableField("original_link")
    @ApiModelProperty("原始链接")
    @ExcelProperty("原始链接")
    private String originalLink;

    @TableField("date")
    @ApiModelProperty("快照时间")
    @ExcelProperty("快照时间")
    private String date;

    @TableField("state")
    @ApiModelProperty("修复状态")
    @ExcelProperty("修复状态")
    private String state;

}
