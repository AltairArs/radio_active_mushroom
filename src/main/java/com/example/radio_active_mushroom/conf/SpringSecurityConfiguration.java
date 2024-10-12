package com.example.radio_active_mushroom.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/**",
                                "/accounts/registration/**",
                                "/accounts/verify/**",
                                "/accounts/registration/done/**",
                                "/accounts/verification/complete/**",
                                "/accounts/verification/failed/**",
                                "/accounts/login/**"
                        ).permitAll()
        ).authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/accounts/logout/**"
                        ).authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
