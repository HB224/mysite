package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
public class MySiteApplication {


        public static void main(String[] args) {
        SpringApplication.run(MySiteApplication.class, args);
    }

}
