package com.example.vich_syst_rgr_back.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Класс - настройка аудитинга
 * Нужен для автоматичесткой проставки даты создания и изменения сущностей через аннотации
 * на данный момент на "Ву" аннотациях стоит заглушка
 */

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
public class AuditorAwareConfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {

        //как я понял нужно для аннотаций "By", но без него вообще не работает
        //так как мы "Ву" аннотациями не пользуемся то делаем заглушку
        @Override
        public Optional<String> getCurrentAuditor() {
            // Can use Spring Security to return currently logged in user
            return  Optional.of("zaglushka");
        }
    }
}
