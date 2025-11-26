package com.velvetslice.pi_velvetslice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Pega o caminho absoluto de onde o projeto está rodando
        String diretorioProjeto = System.getProperty("user.dir");

        String caminhoImagens = "file:" + diretorioProjeto + "/imagens-upload/";

        registry.addResourceHandler("/imagens-upload/**")
                .addResourceLocations(caminhoImagens);
    }
}