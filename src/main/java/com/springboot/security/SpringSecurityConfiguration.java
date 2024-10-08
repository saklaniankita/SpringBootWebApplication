package com.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * We achieve following features using spring Security
 * 1. All URLs stay protected
 * 2. Login form is shown for any unauthorized requests
 * 3.
 * 4.
 */
@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

            UserDetails userDetails1 = createNewUser("ankita", "pass1");
            UserDetails userDetails2 = createNewUser("saklani", "pass2");

            return new InMemoryUserDetailsManager(userDetails1, userDetails2);
        }

        private UserDetails createNewUser(String username, String password) {
            Function<String, String> passwordEncoder
                    = input -> passwordEncoder().encode(input);

            UserDetails userDetails = User.builder()
                    .passwordEncoder(passwordEncoder)
                    .username(username)
                    .password(password)
                    .roles("USER","ADMIN")
                    .build();
            return userDetails;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        http.formLogin(withDefaults());

        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

}

