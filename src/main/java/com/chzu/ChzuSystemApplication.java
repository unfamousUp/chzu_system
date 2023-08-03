package com.chzu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.chzu.mapper")
public class ChzuSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChzuSystemApplication.class, args);
    }

}
