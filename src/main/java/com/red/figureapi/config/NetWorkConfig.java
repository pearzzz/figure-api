package com.red.figureapi.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 设置允许跨域
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 14:00 2022-08-03
 */
public class NetWorkConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}