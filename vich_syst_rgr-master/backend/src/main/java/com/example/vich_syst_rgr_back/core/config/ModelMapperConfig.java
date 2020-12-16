package com.example.vich_syst_rgr_back.core.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * подключение и базовая настройка @see ModelMapper
 * , который используется для задания логики преобразования классов
 */

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final ApplicationContext appContext;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return mapper;
    }

    public interface ModuleMapperConfig{
        void configure();
    }

    /**
     * Метод который после отработки ModelMapperConfig достает из контекста бины содержащие конфиги маппера
     * и запускает их метод "configure()"
     */
    @PostConstruct
    private void configureAllModules(){
        appContext.getBeansOfType(ModuleMapperConfig.class).values().forEach(ModuleMapperConfig::configure);
    }
}
