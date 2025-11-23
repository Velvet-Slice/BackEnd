package com.velvetslice.pi_velvetslice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapeia a URL /imagens-upload/** para a pasta local imagens-upload
        String uploadPath = Paths.get("imagens-upload").toUri().toString();
        registry.addResourceHandler("/imagens-upload/**")
                .addResourceLocations(uploadPath);
    }
}