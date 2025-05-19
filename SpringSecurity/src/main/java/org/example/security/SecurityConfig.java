package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(
            c -> c
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/a/b/c/**").hasAnyRole("ADMIN", "MANAGER", "BASIC_USER")
                    .requestMatchers("/a/b/**").hasAnyRole("ADMIN", "MANAGER")
                    .requestMatchers(HttpMethod.GET, "/a").authenticated()
                    .requestMatchers(HttpMethod.POST, "/a").permitAll()
                    .requestMatchers("/method/**").authenticated()
                    .requestMatchers("/**").hasRole("ADMIN")
                    .anyRequest().denyAll()
        );


        http.csrf(
            c -> c.disable()
        );

        http.headers(headers -> headers
                .frameOptions().sameOrigin()
        );

        return http.build();
    }
}
