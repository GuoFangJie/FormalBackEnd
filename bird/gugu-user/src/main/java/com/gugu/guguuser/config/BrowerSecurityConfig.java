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
               // .loginPage("http://47.94.174.82")
               .loginProcessingUrl("/user/login")
                .successHandler(successHandler).failureHandler(failureHandler)
                //.loginPage("http://localhost:8080/user/login")
                .and()
                .rememberMe()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .antMatcher("/course/**")
//                .antMatcher("/attendance/**")
//                .antMatcher("/class/**")
//                .antMatcher("/question/**")
//                .antMatcher("/request/**")
//                .antMatcher("/round/**")
//                .antMatcher("/seminar/**")
//                .antMatcher("/student/**")
//                .antMatcher("/teacher/**")
//                .antMatcher("/team/**")
//                .antMatcher("/user/**")
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers("/attendance/{attendanceId}/report","/attendance/{attendanceId}/ppt","/attendance/{seminarKlassId}"
//                        + "/course/**","class/**","/request/**","/round/**","/seminar/**","/student","/Teacher","/team/{teamId}","/user/**").hasRole("Teacher")
//
//               .antMatchers("/attendance/**","/course/{courseId}/isMainTeam","/course/{courseId}/isMainSeminar","/course/{courseId}/round/{roundId}",
//                      "course/{courseId}/application" ,"course/{courseId}/round","course/{courseId}/share","/course/{courseId}/class","/course/{courseId}/noTeam",
//                      "/course/{courseId}/teams","/course/{courseId}/team","/course/{courseId}/score","/course/{courseId}","/course",
//                       "/request/seminarshare","/request/teamshare","/round/{roundId}/team/{teamId}/roundscore",
//                       "/round/{roundId}/team/{teamId}","/round/{roundId}/roundscore","/round/{roundId}","/round/{roundId}/seminar",
//                       "/seminar/{seminarId}/class","/seminar/{seminarId}","/seminar/{seminarId}/class/{classId}","/seminar/{seminarId}/team/{teamId}/seminarscore",
//                       "/seminar/{seminarId}/seminarscore","/seminar/{seminarKlassId}/seminarEnter","/student/**","/team/**","/user/**","/question/**").hasAnyRole("Student","Teacher")

               .antMatchers("/user").permitAll()
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
      // auth.authenticationProvider(myAuthenticationProvider());
    }



}