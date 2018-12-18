package com.gugu.guguuser.config.Handler;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class JWTBasicFilter extends BasicAuthenticationFilter {
    public JWTBasicFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("GuGuBird")) {
            System.out.println("没找到JWT");
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //super.rememberMeServices.loginSuccess(request, response, authResult);

        onSuccessfulAuthentication(request, response,authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println("开始解析");
        if (token != null) {
            // parse the token.
            token=token.replace("GuGuBird","");
            ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

            Claims claims = Jwts.parser()
                    .setSigningKey("MyJwtSecret")
                    .parseClaimsJws(token)
                    .getBody();
            //校验是否过期
            if(System.currentTimeMillis()>Long.parseLong((String)claims.get("exp"))) {
                return null;
            }
            Map<String,Object> objectMap= (Map<String, Object>) claims.get("role");
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(objectMap.get("authority").toString());
            simpleGrantedAuthorities.add(simpleGrantedAuthority);
            System.out.println(objectMap.get("authority"));
            return new UsernamePasswordAuthenticationToken("", "", simpleGrantedAuthorities);
        }
        return null;
    }
}
