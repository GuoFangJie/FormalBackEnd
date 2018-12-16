package com.gugu.guguadmin.controller;


import com.gugu.guguadmin.service.TeacherService;
import com.gugu.guguadmin.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 管理员根据教师ID删除教师账号
     * @param teacherId
     * @return
     */
    @DeleteMapping("/{teacherId}")
    public void deleteTeacherById(@PathVariable long teacherId, HttpServletResponse httpServletResponse){
        try {
            teacherService.deleteTeacherById(teacherId);
        }catch (Exception e){
            httpServletResponse.setStatus(404);
        }
    }

}
