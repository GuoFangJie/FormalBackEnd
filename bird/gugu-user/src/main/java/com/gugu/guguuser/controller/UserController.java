package com.gugu.guguuser.controller;


import com.gugu.gugumodel.pojo.vo.UserAccountVO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @PutMapping("modifycode")
    public boolean getModifyCode(@RequestBody UserAccountVO userAccountVO){
         //等待数据库标准组对用户表的修改
        return true;
    }

}
