package com.cihexample.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    private String greeting;

    public String getGreeting(){
        return this.greeting;
    }
    public void setGeeting(String geeting){
        this.greeting = geeting;
    }

}
