package com.gugu.guguuser.controller;

import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.pojo.vo.ActiveUserVO;
import com.gugu.guguuser.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/active")
    public boolean activeTeacher(@RequestBody ActiveUserVO activeUserVO, HttpServletRequest httpServletRequest) throws NotFoundException{
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        activeUserVO.setUserId(userId);
        if(1==1){
            throw new NotFoundException("没有找到相应的教师");
        }
        return teacherService.activeTeacher(activeUserVO);
    }

}
