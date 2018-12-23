package com.gugu.guguadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gugu.gugumodel", "com.gugu.guguadmin"})
public class GuguAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuguAdminApplication.class, args);
    }

}

