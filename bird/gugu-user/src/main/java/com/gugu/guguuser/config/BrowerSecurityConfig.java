package com.gugu.guguuser.config;

import com.gugu.guguuser.config.handler.FailureHandler;
import com.gugu.guguuser.config.handler.JWTBasicFilter;
import com.gugu.guguuser.config.handler.MyAuthenticationProvider;
import com.gugu.guguuser.config.handler.SuccessHandler;
import com.gugu.guguuser.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SuccessHandler successHandler;
    @Autowired
    FailureHandler failureHandler;
    @Autowired
    SecurityService securityService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
               //.loginPage("/toLoginPage")
                .loginProcessingUrl("/user/login")
                .successHandler(successHandler).failureHandler(failureHandler)
                //.loginPage("http://localhost:8080/user/login")
                .and()
                .rememberMe()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
               // .antMatchers("/test/nice").hasRole("Teacher")
               // .antMatchers("/student/**").hasRole("Teacher")
                .antMatchers("/**/**","/user/{account}/login","/toLoginPage").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JWTBasicFilter(authenticationManager()))
                .csrf().disable();

    }
    @Bean
    public MyAuthenticationProvider myAuthenticationProvider(){
        return new MyAuthenticationProvider();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService)
                .passwordEncoder(new MyPasswordEncoder());

//
//        auth.authenticationProvider(myAuthenticationProvider());
    }



}