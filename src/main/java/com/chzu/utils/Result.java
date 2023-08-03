package com.chzu.utils;


import com.chzu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> extends User {
    private Integer code;
    private String message;
    private T data;
}
