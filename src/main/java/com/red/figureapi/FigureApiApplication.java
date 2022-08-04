package com.red.figureapi;

import com.red.figureapi.config.NetWorkConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan
@Slf4j
public class FigureApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FigureApiApplication.class, args);
    }

    /**
     * TODO 设置允许跨域
    */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new NetWorkConfig();
    }
}
