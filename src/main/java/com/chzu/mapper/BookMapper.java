package com.chzu.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chzu.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

// 注解配置mapper接口
@Mapper
// 指定表名
@TableName("book")
public interface BookMapper extends BaseMapper<Book> {

    // @Insert("INSERT INTO book (book_name, price) VALUES (#{bookName}, #{price})")
    // int insertSelective(Book book);

   Book selectBookById(@Param("id") Integer id);

}
