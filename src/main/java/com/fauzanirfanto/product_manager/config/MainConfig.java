package com.fauzanirfanto.product_manager.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MainConfig {

    @Autowired
    private Environment env;

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
