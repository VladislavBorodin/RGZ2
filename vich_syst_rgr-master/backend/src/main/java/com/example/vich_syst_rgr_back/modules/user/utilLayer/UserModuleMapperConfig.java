package com.example.vich_syst_rgr_back.modules.user.utilLayer;

import com.example.vich_syst_rgr_back.core.config.ModelMapperConfig;
import com.example.vich_syst_rgr_back.core.domain.Role;
import com.example.vich_syst_rgr_back.core.domain.User;
import com.example.vich_syst_rgr_back.modules.user.dtos.RegisterUserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * класс технического слоя, содержит в себе настройку @see ModelMapper
 */

@AllArgsConstructor
@Component
@Scope("prototype")
public class UserModuleMapperConfig implements ModelMapperConfig.ModuleMapperConfig {

    private final ModelMapper mapper;
    private final SetStringToSetRoleConverter setRoleConverter;

    @Override
    public void configure() {
        this.mapper.typeMap(RegisterUserDto.class, User.class)
                .addMappings(mapping -> mapping.using(setRoleConverter)
                        .map(RegisterUserDto::getAuthorities, User::setAuthorities));
    }

    @Component
    @Scope("prototype")
    private static class SetStringToSetRoleConverter extends AbstractConverter<Set<String>, Set<Role>> {
        @Override
        protected Set<Role> convert(Set<String> source) {
            return source.stream().map(Role::new).collect(Collectors.toSet());
        }
    }

}
