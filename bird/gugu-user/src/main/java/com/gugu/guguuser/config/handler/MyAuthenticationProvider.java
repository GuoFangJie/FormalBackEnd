package com.gugu.guguuser.config.handler;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

public class MyAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("到这里了");
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority("ROLE_Teacher");
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities=new ArrayList<>();
        simpleGrantedAuthorities.add(simpleGrantedAuthority);
        return new UsernamePasswordAuthenticationToken("123","123456",simpleGrantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
