package com.velvetslice.pi_velvetslice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Pega o caminho absoluto da pasta "imagens-upload" na raiz do projeto
        String uploadPath = Paths.get("imagens-upload").toUri().toString();

        // Diz ao Spring: "Tudo que vier na URL /imagens-upload/**, procure na pasta
        // física definida acima"
        registry.addResourceHandler("/imagens-upload/**")
                .addResourceLocations(uploadPath);
    }
}