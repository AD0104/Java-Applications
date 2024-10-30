package com.itembox.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.itembox.configuration.service.ApplicationUserService;

@EnableWebSecurity
@Configuration
public class ItemboxApplicationWebSecurityConfiguration {
        @Autowired
        protected ApplicationUserService applicationUserService;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(
                                        (authorizeHttpRequests) -> authorizeHttpRequests
                                            .requestMatchers("/auth/**")
                                            .permitAll()
                                            .requestMatchers("/css/**", "/js/**", "/images/**")
                                            .permitAll()
                                            .anyRequest()
                                            .authenticated()
                                 )
                                .httpBasic(Customizer.withDefaults())
                                .formLogin(form -> form
                                                .loginPage("/auth/login")
                                                .permitAll()
                                                .defaultSuccessUrl("/")
                                )
                                .logout(config -> config.logoutSuccessUrl("/"))
                                .build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return this.applicationUserService;
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
                daoAuthenticationProvider.setUserDetailsService(this.applicationUserService);
                daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
                return daoAuthenticationProvider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
