package com.danbro.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.danbro.shiro.test.mapper")
public class ShiroTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroTestApplication.class, args);
    }

}
