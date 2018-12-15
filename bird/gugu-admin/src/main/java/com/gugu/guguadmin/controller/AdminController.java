package com.gugu.guguadmin.controller;

import com.gugu.guguadmin.service.AdminService;
import com.gugu.gugumodel.pojo.entity.AdminEntity;
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
    AdminService adminService;

    /**
     * 管理员登录
     * @param adminEntity
     * @return
     */
    @GetMapping("/login")
    public AdminEntity adminLogin(@RequestBody AdminEntity adminEntity){
        return adminService.adminLogin(adminEntity);
    }
}
