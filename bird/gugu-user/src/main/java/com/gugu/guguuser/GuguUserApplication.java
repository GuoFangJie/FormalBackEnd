package com.gugu.guguuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@SpringBootApplication
@ComponentScan(basePackages = {"com.gugu.gugumodel",
        "com.gugu.guguuser"})
public class GuguUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuguUserApplication.class, args);
    }

}

