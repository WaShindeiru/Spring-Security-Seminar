package org.example.config;

import org.example.filter.LoggingFilter;
import org.example.filter.StaticKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                        new StaticKeyAuthenticationFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAfter(
                        new LoggingFilter(),
                        BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }
}
