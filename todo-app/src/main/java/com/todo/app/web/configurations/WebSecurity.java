package com.todo.app.web.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    protected UserSecurityDetails userSecurityDetails;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{

        return http
            .csrf(Customizer.withDefaults())
            .authorizeHttpRequests( httpRequest -> {
                httpRequest.requestMatchers("/", "/js/**", "/css/**").permitAll();
                httpRequest.requestMatchers("/signup").permitAll();
                httpRequest.requestMatchers("/logout").permitAll();
                httpRequest.requestMatchers("/user/**").hasRole("USER");
                httpRequest.requestMatchers("/admin/**").hasRole("ADMIN");
            })
            .formLogin( httpFormLoginConfigurer -> {
                httpFormLoginConfigurer
                    .loginPage("/login")
                    .successHandler(new AuthenticationSuccessHandler())
                    .permitAll();
            })
            .logout( (logout) -> logout.logoutUrl("/logout").permitAll())
            .authenticationProvider(authenticationProvider())
            .build();

    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this.userSecurityDetails;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
