package com.chzu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("book")
@ApiModel("书籍实体类") // 配置Model实体类信息
public class Book {
    // 设置Id的生成策略：自增
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;

    @TableField("book_name")
    @ApiModelProperty("书籍名称")
    private String bookName;

    @TableField("writer")
    @ApiModelProperty("作者")
    private String writer;

    @TableField("price")
    @ApiModelProperty("价格")
    private String price;

    @TableField("loc")
    @ApiModelProperty("出版社")
    private String loc;

    @TableField("state")
    @ApiModelProperty("状态")
    private String state;
}
