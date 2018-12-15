package com.gugu.guguadmin.controller;


import com.gugu.guguadmin.service.StudentService;
import com.gugu.guguadmin.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 管理员根据学生ID删除学生账号
     * @param studentId
     * @return
     */
    @DeleteMapping("/{studentId}")
    public void deleteStudentById(@PathVariable long studentId, HttpServletResponse httpServletResponse){
        try {
            studentService.deleteStudentById(studentId);
        }catch (Exception e){
            httpServletResponse.setStatus(404);
        }
    }

    /**
     * 管理员获取学生账号信息
     * @param
     * @return
     */

}
