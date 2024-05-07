package com.grey.saas.security;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Setter
public class CustomSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            authorize.anyRequest().authenticated();
        });
        return http.build();
    }

//    @Bean
//    public CustomAuthenticationFilter authenticationFilter() throws Exception {
//        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//        // Add logic for setting authenticationFailureHandler and authenticationSuccessHandler
//        // These are already configured in the securityFilterChain bean
//        return filter;
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        CustomUserDetailsAuthenticationProvider provider = new CustomUserDetailsAuthenticationProvider(
                this.passwordEncoder(), userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
