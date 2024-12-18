package com.example.order.order.Bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.order.order.Util.HandleException;

@Configuration
public class AppConfig {

    @Bean
    public HandleException handleException() {
        return new HandleException();
    }
}
