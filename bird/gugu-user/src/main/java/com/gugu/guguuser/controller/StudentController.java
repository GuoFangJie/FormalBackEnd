package com.gugu.guguuser.controller;


import com.gugu.guguuser.service.StudentService;
import com.gugu.guguuser.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
