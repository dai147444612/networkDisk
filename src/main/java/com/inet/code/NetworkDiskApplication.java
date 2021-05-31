package com.inet.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.inet.demo.mapper")
public class NetworkDiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkDiskApplication.class, args);
    }

}
