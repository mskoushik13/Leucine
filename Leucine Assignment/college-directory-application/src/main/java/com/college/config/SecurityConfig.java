package com.college.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure in-memory authentication or use a custom UserDetailsService
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .and()
                .withUser("faculty")
                .password(passwordEncoder().encode("facultyPass"))
                .roles("FACULTY_MEMBER")
                .and()
                .withUser("student")
                .password(passwordEncoder().encode("studentPass"))
                .roles("STUDENT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic(); // Use Basic Authentication
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoder for encrypting passwords
    }
}
