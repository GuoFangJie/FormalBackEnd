package com.gugu.guguuser.config.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FailureHandler implements AuthenticationFailureHandler {
    @Value("${errLogin}")
    String errLogin;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
       e.printStackTrace();
        if(e instanceof BadCredentialsException){
            httpServletResponse.setStatus(400);
            httpServletResponse.sendRedirect(errLogin);
        }
        else if(e instanceof UsernameNotFoundException) {
            httpServletResponse.setStatus(400);
            httpServletResponse.sendRedirect(errLogin);
        }
    }
}
