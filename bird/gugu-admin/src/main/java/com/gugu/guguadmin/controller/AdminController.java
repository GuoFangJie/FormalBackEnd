package com.gugu.guguadmin.controller;

import com.gugu.gugumodel.pojo.entity.AdminEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import com.gugu.guguadmin.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 管理员控制器
 * @author TYJ
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;

    /**
     * 管理员登录
     * @param adminEntity
     * @return
     */
    @GetMapping("/login")
    public AdminEntity adminLogin(@RequestBody AdminEntity adminEntity){
        return adminService.adminLogin(adminEntity);
    }

    /**删除教师账号
     * @param
     * @return
     */

}
