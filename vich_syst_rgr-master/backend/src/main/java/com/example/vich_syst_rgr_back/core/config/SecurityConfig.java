package com.example.vich_syst_rgr_back.core.config;

import com.example.vich_syst_rgr_back.modules.user.repositories.UserRepo;
import com.example.vich_syst_rgr_back.modules.user.utilLayer.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Класс, наследуется от @see WebSecurityConfigurerAdapter
 * представляет собой настройку авторизации/аутентификации
 * на данным момент настроен на Basic Auth
 */

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService detailsService;

    /**
    * В данном методе переопределяется какой UserDetailService будет использоваться
    * и сообщается какой Encoder используется для хеширования паролей
    */

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * в данном методе производится настройка безопасности web-приложения
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    //список ссылок доступных без ау-ции
                    //примечание: под неверной аутентификацией всеравно будет разворачивать на 401
                    .antMatchers("/api/reg/bankUser", "/swagger-ui.html","/api/v2/**").permitAll()
                    //ссылки для юзера банковской части приложения
                    .antMatchers("/api/bankUser/**").hasAuthority("BANK_USER")
                    //ссылки для админа
                    .antMatchers("/api/collector/**").hasAuthority("COLLECTOR")
                    //включение ау-ции
                    .anyRequest().authenticated()
                .and()
                    .cors()
                .and()
                    .httpBasic()
                .and()
                    .formLogin()
                    .loginPage("/api/login")
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/")
                .and()
                    .csrf()
                    .disable();
                    //отключение защиты от межсайтовой подделки запроса, так как у нас REST Api
    }

    /**
     * Класс, имплементация @see UserDetailsService
     * содержит логику по превращению @see User, найденного по логину, в @see UserDetails
     * для последующей передачи механизму аутентификации/авторизации
     */

    @Service
    @AllArgsConstructor
    public static class MyUserDetailsService implements UserDetailsService {

        private final UserRepo userRepository;

        @Override

        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            //ищет юзера по логину и проверяет на нулл с выбросом ошибки
            return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        }
    }

    //создает бин класса хеширующиего пароли
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
