package com.japp.list.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("userlist")
public class ListConfig {

    private int allowedsize;

}
