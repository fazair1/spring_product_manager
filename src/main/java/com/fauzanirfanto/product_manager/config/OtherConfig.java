package com.fauzanirfanto.product_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:other.properties")
public class OtherConfig {

    private static Integer pageDefault;

    public static Integer getPageDefault() {
        return pageDefault;
    }

    @Value("${page.default}")
    private void setPageDefault(Integer pageDefault) {
        OtherConfig.pageDefault = pageDefault;
    }

}
