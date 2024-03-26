package com.example.userservice.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(CsrfConfigurer:: disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/users/**", "/user-service/**").permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()

                )
                .headers((headerConfig) -> headerConfig.frameOptions((HeadersConfigurer.FrameOptionsConfig::disable)));

        return http.build();

    }
}
