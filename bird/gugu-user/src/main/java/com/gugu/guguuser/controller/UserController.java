package com.gugu.guguuser.controller;


import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.guguuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 发送密码到用户的邮箱
     * @param httpServletRequest
     * @return
     */
    @GetMapping("password")
    public boolean sendPasswordToUser(HttpServletRequest httpServletRequest){
        String role= (String) httpServletRequest.getAttribute("role");
        String userId=httpServletRequest.getAttribute("userId").toString();
        return userService.sendPasswordToUser(Long.parseLong(userId),role);
    }

    /**
     * 修改用户密码
     * @param httpServletRequest
     * @param password
     * @return
     */
    @PutMapping("password")
    public boolean changePassword(HttpServletRequest httpServletRequest,@RequestBody String password){
        String role=httpServletRequest.getAttribute("role").toString();
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return userService.changePassword(role,password,userId);
    }

    /**
     * 获取用户详细信息
     * @param httpServletRequest
     * @return
     */
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
    @PutMapping("email")
    public boolean changeEmail(HttpServletRequest httpServletRequest,@RequestBody String email){
        String role=httpServletRequest.getAttribute("role").toString();
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return userService.changePassword(role,email,userId);
    }
}
