package com.red.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 16:37 2022-08-09
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterStarter {
    public static void main(String[] args) {
        SpringApplication.run(RegisterStarter.class, args);
    }
}