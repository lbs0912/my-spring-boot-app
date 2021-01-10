package com.lbs.spring.my_app.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "config.test")
@Component
@Data
public class ConfigBean {
    private String name;
    private int age;
}
