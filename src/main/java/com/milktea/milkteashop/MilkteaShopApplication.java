package com.milktea.milkteashop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.milktea.milkteashop.dao")
public class MilkteaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MilkteaShopApplication.class, args);
    }
}
