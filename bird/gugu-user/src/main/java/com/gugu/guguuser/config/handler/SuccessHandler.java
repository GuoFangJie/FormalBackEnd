package com.gugu.guguuser.config.handler;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.entity.SecurityUserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
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
        Long userId;
        System.out.println(objects[0]);
        if(objects[0].toString().equals("ROLE_Teacher")){
            userId=teacherDao.getTeacherByAccount(((SecurityUserEntity)authentication.getPrincipal()).getUsername());
        }else{
            userId=studentDao.getStudentByAccount(((SecurityUserEntity)authentication.getPrincipal()).getUsername());
        }
        claim.put("userId",Long.toString(userId));
        String token = Jwts.builder()
                .setHeader(head)
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS256,"MyJwtSecret")
                .compact();
        Cookie cookie=new Cookie("Authorization","GuGuBird"+token);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        httpServletResponse.addCookie(cookie);
        System.out.println("JWT安装完成");
        httpServletResponse.sendRedirect("http://localhost:8081/#/TeacherMainPage");
    }
}
