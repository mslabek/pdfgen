package com.application.preprocessing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class PlaceholderConfig {

    @Bean
    public Pattern templatePattern() {
        return Pattern.compile("\\$\\{(.+?)}");
    }

}
