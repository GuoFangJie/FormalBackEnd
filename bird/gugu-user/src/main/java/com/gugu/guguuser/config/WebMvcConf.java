package com.gugu.guguuser.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
    public static final String separator=File.separator;
    @Value("${reportPath}")
    String reportPath;
    @Value("${reportPathInServer}")
    String reportPathInServer;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(reportPath).addResourceLocations("file:"+reportPathInServer);
    }

}
