package com.red.figureapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@Slf4j

public class FigureApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FigureApiApplication.class, args);
    }

}
