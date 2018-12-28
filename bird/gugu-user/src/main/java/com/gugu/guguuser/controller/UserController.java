package com.gugu.guguuser.controller;


import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.guguuser.controller.vo.EmailVO;
import com.gugu.guguuser.controller.vo.PasswordVO;
import com.gugu.guguuser.service.StudentService;
import com.gugu.guguuser.service.TeacherService;
import com.gugu.guguuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    /**
     * 发送密码到用户的邮箱
     * @param
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("password/{account}")
    public boolean sendPasswordToUser(HttpServletResponse httpServletResponse, @PathVariable("account")String account){
        Long userId;
        String role;
        if(teacherService.getIdByAccount(account)!=null){
            role="ROLE_Teacher";
            userId=teacherService.getIdByAccount(account);
        }else if(studentService.getIdByAccount(account)!=null){
            role="ROLE_Student";
            userId=studentService.getIdByAccount(account);
        }else{
            httpServletResponse.setStatus(404,"用户不存在");
            return false;
        }
        return userService.sendPasswordToUser(userId,role);
    }

    /**
     * 修改用户密码
     * @param httpServletRequest
     * @param password
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @PutMapping("password")
    public boolean changePassword(HttpServletRequest httpServletRequest, @RequestBody PasswordVO password){
        String role=httpServletRequest.getAttribute("role").toString();
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return userService.changePassword(role,password.getPassword(),userId);
    }

    /**
     * 获取用户详细信息
     * @param httpServletRequest
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("information")
    public StudentEntity getUserInfo(HttpServletRequest httpServletRequest){
        String role=httpServletRequest.getAttribute("role").toString();
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return userService.getUserInfo(role,userId);
    }

    /**
     * 修改用户的邮箱
     * @param httpServletRequest
     * @param email
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @PutMapping("email")
    public boolean changeEmail(HttpServletRequest httpServletRequest, @RequestBody EmailVO email){
        String role=httpServletRequest.getAttribute("role").toString();
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return userService.changeEmail(role,email.getEmail(),userId);
    }

    @GetMapping("")
    public boolean hasJwt(HttpServletRequest httpServletRequest){
        try {
            String role = httpServletRequest.getAttribute("role").toString();
            System.out.println(role);
        }catch (NullPointerException e){
            return false;
        }
        return true;
    }

    @PostMapping("out")
    public boolean logout(HttpServletResponse httpServletResponse){
        Cookie cookie=new Cookie("role","");
        return true;
    }
}
