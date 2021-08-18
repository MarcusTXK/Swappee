package com.swappee.utils.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for allowing of Cross-Origin Resource Sharing for front end
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${frontend.baseurl}")
    private String baseurl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(baseurl);
    }
}
