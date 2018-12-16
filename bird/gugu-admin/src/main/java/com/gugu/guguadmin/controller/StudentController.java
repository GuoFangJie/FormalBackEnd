package com.gugu.guguadmin.controller;


import com.gugu.guguadmin.service.StudentService;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

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
     * 管理员获取所有学生信息
     * @return ArrayList
     */
    @GetMapping("/")
    public ArrayList<StudentEntity> getStudents(){
        return studentService.getStudents();
    }

    /**
     * 管理员重置学生密码
     * @param studentId
     */
    @PutMapping("/{studentId}/password")
    public void resetStudentPassword(@PathVariable Long studentId,HttpServletResponse httpServletResponse){
        try{
            studentService.resetStudentPassword(studentId);
        }
        catch (Exception e){
            httpServletResponse.setStatus(404);
        }
    }

    /**
     * 管理员修改学生信息，包括账号，姓名，邮箱
     * @param studentId
     */
    @PutMapping("/{studentId}/information")
    public void changeStudentInformation(@PathVariable Long studentId, @RequestBody StudentEntity studentEntity,HttpServletResponse httpServletResponse){
        studentEntity.setId(studentId);
        try {
            studentService.changeStudentInformation(studentEntity);
        }
        catch (Exception e){
            httpServletResponse.setStatus(404);
        }

    }



}
