package com.example.appnewssite.config;

import com.example.appnewssite.security.JwtFilter;
import com.example.appnewssite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // ishlatishi uchun RoleControllerdagi 24 25 qator uchun
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    AuthService authService;
    @Autowired
    JwtFilter jwtFilter;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/register","/api/auth/login")
                .permitAll()
                .anyRequest()
                .authenticated();
        /**
         * Biz aytyapmizki httpga JwtFilter ishlasin <br>
         * (hali parol loginlani solishtirmasda sistemaga kirmasdan) <br>
         * UsernamePasswordAuthenticationFilter.class dan oldin dedik
         */
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        /**
         * SPRING SECURITYGA SESSIONGA USHLAB OLMASLIGINI BUYIRYAPMIZ
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
