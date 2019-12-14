package com.joe.app.personalblog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
//                .anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler((req, res, exception) -> {
                    logger.info("No Access.");
                    Map<String, String> result = new HashMap<>();
                    result.put("code", "403");
                    result.put("result", "unauthenticated");
                    res.setContentType("application/json;charset=utf-8");
                    res.setStatus(403);
                    PrintWriter out = res.getWriter();
                    out.write(objectMapper.writeValueAsString(result));
                    out.flush();
                    out.close();
                }).authenticationEntryPoint((req, res, exception) -> {
                    logger.info("No authenticated.");
                    Map<String, String> result = new HashMap<>();
                    result.put("code", "403");
                    result.put("result", "unauthenticated");
                    res.setContentType("application/json;charset=utf-8");
                    res.setStatus(403);
                    PrintWriter out = res.getWriter();
                    out.write(objectMapper.writeValueAsString(result));
                    out.flush();
                    out.close();
                }).and()
                .formLogin()
                .loginPage("/Admin.html").permitAll()
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    Map<String, String> result = new HashMap<>();
                    result.put("code", "200");
                    result.put("result", "success");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(result));
                    out.flush();
                    out.close();
                }).failureHandler((request, response, authentication) -> {
                    Map<String, String> result = new HashMap<>();
                    result.put("code", "500");
                    result.put("result", "fail");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(result));
                    out.flush();
                    out.close();
                }).and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
