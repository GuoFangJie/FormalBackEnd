package com.gugu.guguuser.config.handler;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        Cookie []cookies=request.getCookies();
        for(int i=0;i<cookies.length;i++) {
            System.out.println(cookies[i].getName());
            if (cookies[i].getName().equals("Authorization")) {
                String token=cookies[i].getValue();
                UsernamePasswordAuthenticationToken authentication = getAuthentication(request,token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                onSuccessfulAuthentication(request, response, authentication);
                chain.doFilter(request, response);
                return;
            }
        }
        System.out.println("没找到JWT");
        chain.doFilter(request, response);
        return;

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,String token) {
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
//            if(System.currentTimeMillis()>Long.parseLong((String)claims.get("exp"))) {
//                return null;
//            }
            Map<String,Object> objectMap= (Map<String, Object>) claims.get("role");
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(objectMap.get("authority").toString());
            simpleGrantedAuthorities.add(simpleGrantedAuthority);
            System.out.println(objectMap.get("authority"));
            request.setAttribute("userId",claims.get("userId"));
            request.setAttribute("role",objectMap.get("role"));
            return new UsernamePasswordAuthenticationToken("", "", simpleGrantedAuthorities);
        }
        return null;
    }
}
