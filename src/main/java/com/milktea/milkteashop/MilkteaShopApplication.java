package com.milktea.milkteashop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
@MapperScan("com.milktea.milkteashop.dao")
//@ComponentScan("com.milktea.milkteashop.job")
public class MilkteaShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MilkteaShopApplication.class, args);
    }
}
