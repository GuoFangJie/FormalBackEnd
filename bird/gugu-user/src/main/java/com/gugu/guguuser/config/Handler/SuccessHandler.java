package com.gugu.guguuser.config.Handler;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        Map<String,Object> head=new HashMap<>();
        head.put("alg","SHA256");
        head.put("typ","JWT");
        Map<String,Object> claim=new HashMap<>();
        Object[] objects=authentication.getAuthorities().toArray();
        claim.put("role",objects[0]);
        claim.put("exp",Long.toString(System.currentTimeMillis()+1000*60*10));
        String token = Jwts.builder()
                .setHeader(head)
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS256,"MyJwtSecret")
                .compact();
        httpServletResponse.setHeader("Authorization", "GuGuBird" + token);
        System.out.println("JWT安装完成");
        httpServletResponse.sendRedirect("http://47.94.174.82/#/TeacherMainPage");
    }
}
