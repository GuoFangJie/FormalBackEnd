package com.gugu.guguuser.controller;

import com.gugu.gugumodel.pojo.vo.ActiveUserVO;
import com.gugu.guguuser.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
        activeUserVO.setUserId(userId);
        return studentService.activeStudent(activeUserVO);
    }
}
