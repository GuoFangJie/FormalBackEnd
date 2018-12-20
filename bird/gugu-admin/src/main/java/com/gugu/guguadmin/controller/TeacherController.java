package com.gugu.guguadmin.controller;


import com.gugu.guguadmin.service.TeacherService;
import com.gugu.gugumodel.entity.TeacherEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**@author ljy
     * 管理员新建教师账号
     * @param
     * @return
     */
    @PostMapping("")
    public Long newTeacher(@RequestBody TeacherEntity teacherEntity,HttpServletResponse httpServletResponse) throws Exception{
        Byte a=0;
        teacherEntity.setIsActive(a);
        return teacherService.newTeacher(teacherEntity);
    }


    /**
     * 管理员根据教师ID删除教师账号
     * @param teacherId
     * @return
     */
    @DeleteMapping("/{teacherId}")
    public void deleteTeacherById(@PathVariable long teacherId, HttpServletResponse httpServletResponse) throws NotFoundException {
        teacherService.deleteTeacherById(teacherId);
    }

    /**
     * 管理员获取所有教师信息
     * @return ArrayList
     */
    @GetMapping("")
    public ArrayList<TeacherEntity> getTeachers(){
        return teacherService.getTeachers();
    }


    /**
     * 管理员重置教师密码
     * @param teacherId
     */
    @PutMapping("/{teacherId}/password")
    public void resetTeacherPassword(@PathVariable Long teacherId,HttpServletResponse httpServletResponse){
        teacherService.resetTeacherPassword(teacherId);
    }

    /**
     * 管理员修改教师信息，包括账号，姓名，邮箱
     * @param teacherId
     */
    @PutMapping("/{teacherId}/information")
    public void changeTeacherInformation(@PathVariable Long teacherId, @RequestBody TeacherEntity teacherEntity,HttpServletResponse httpServletResponse) throws SQLException{
        teacherEntity.setId(teacherId);
        teacherService.changeTeacherInformation(teacherEntity);
    }

    /**
     * 管理员根据教师教工号或姓名查找教师
     * @param identity
     */
    @GetMapping("/searchteacher")
    public ArrayList<TeacherEntity> searchTeacher(String identity){
        return teacherService.searchTeacher(identity);
    }

}
