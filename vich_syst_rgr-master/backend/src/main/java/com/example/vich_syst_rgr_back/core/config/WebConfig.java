package com.example.vich_syst_rgr_back.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Настройка CORS
     * @param registry - параметры CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //пока нет необходимости в другом - корс настроен помечать все запросы
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }

}
