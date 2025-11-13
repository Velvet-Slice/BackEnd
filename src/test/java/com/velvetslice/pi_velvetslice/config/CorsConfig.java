package com.velvetslice.pi_velvetslice.config; // Ajuste o pacote conforme necessário

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Mapeia para todos os endpoints da API (/**)
        registry.addMapping("/**")
                // Live Server do VS Code, geralmente é localhost:5500 ou 127.0.0.1:5500
                .allowedOrigins("http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
