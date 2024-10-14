package com.example.radio_active_mushroom.conf;

import com.example.radio_active_mushroom.authentication.CustomAuthenticationFilter;
import com.example.radio_active_mushroom.authentication.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {

    private CustomAuthenticationFilter customAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new CustomAuthenticationFilter(authenticationManager);
    }

    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAt(customAuthenticationFilter(new ProviderManager(customAuthenticationProvider)), UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(customAuthenticationProvider);
        http.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));
        http.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/img/**",
                                "/fonts/**",
                                "/favicon.ico",
                                "/",
                                "/accounts/registration/",
                                "/accounts/verify/**",
                                "/accounts/registration/done/",
                                "/accounts/verification/complete/",
                                "/accounts/verification/failed/",
                                "accounts/login/*",
                                "accounts/login/process/"
                        ).permitAll()
                        .requestMatchers("/accounts/logout/").hasAnyRole("ADMIN", "USER", "STAFF")
                )
                .formLogin(form -> form
                        .loginPage("/accounts/login/")
                        .successForwardUrl("/")
                        .loginProcessingUrl("/accounts/login/process/")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/accounts/logout")
                        .logoutSuccessUrl("/accounts/login/")
                );
        return http.build();
    }
}
