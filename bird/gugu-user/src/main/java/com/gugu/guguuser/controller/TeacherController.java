package com.gugu.guguuser.controller;

import com.gugu.gugumodel.pojo.vo.ActiveUserVO;
import com.gugu.guguuser.service.TeacherService;
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
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 激活老师账号
     * @param activeUserVO
     * @param httpServletRequest
     * @return
     */
    @PutMapping("acitve")
    public boolean activeTeacher(@RequestBody ActiveUserVO activeUserVO, HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        activeUserVO.setUserId(userId);
        return teacherService.activeTeacher(activeUserVO);
    }
}
