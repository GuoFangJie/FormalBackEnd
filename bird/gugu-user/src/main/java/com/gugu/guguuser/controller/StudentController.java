package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.guguuser.controller.vo.ActiveUserVO;
import com.gugu.guguuser.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author ren
 */
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 激活学生账号
     * @param activeUserVO
     * @param httpServletRequest
     * @return
     */
    @PutMapping("active")
    public boolean activeStudent(@RequestBody ActiveUserVO activeUserVO, HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setEmail(activeUserVO.getEmail());
        studentEntity.setPassword(activeUserVO.getPassword());
        studentEntity.setId(userId);
        return studentService.activeStudent(studentEntity);
    }

    /**ljy
     * 根据account搜索学生
     * @return
     */
    @GetMapping("")
    public StudentEntity searchStudent(String account,Long classId){
        System.out.println(account);
        StudentEntity studentEntity=(studentService.searchStudent(account)).get(0);
        studentEntity.setTeamId(studentService.getStudentTeam(studentEntity.getId(),classId));
        return studentEntity;
    }

    /**
     * 获取当前账号
     * @param httpServletRequest
     * @return
     */
    @GetMapping("getaccount")
    public String getAccount(HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return studentService.getStudentById(userId).getAccount();
    }
}
