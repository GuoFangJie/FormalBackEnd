package com.gugu.guguuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@SpringBootApplication(scanBasePackages = {"com.gugu.gugumodel","com.gugu.guguuser"})
@ComponentScan("com.gugu.gugumodel")
public class GuguUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuguUserApplication.class, args);
    }

}

