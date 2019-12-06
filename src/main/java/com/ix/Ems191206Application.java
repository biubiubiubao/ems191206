package com.ix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ix.dao")
public class Ems191206Application {
    public static void main(String[] args) {
        SpringApplication.run(Ems191206Application.class);
    }
}
