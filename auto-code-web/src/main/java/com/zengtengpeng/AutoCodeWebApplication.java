package com.zengtengpeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.*.*.dao")
public class AutoCodeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoCodeWebApplication.class, args);
    }
}