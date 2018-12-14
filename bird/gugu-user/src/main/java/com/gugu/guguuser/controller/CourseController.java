package com.gugu.guguuser.controller;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import com.gugu.gugumodel.pojo.vo.UserAccountVO;
import com.gugu.guguuser.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;

    /**
     * 获取与用户相关的课程
     * @param userAccountVO
     * @return
     */
    @GetMapping("/")
    public ArrayList<SimpleCourseEntity> getCourseByUser(@RequestBody UserAccountVO userAccountVO){
        return courseService.findSimpleCourseEntityByStudentId(userAccountVO.getUserAccount());
    }

    /**
     * 新建课程，返回课程id
     * @param courseEntity
     * @return
     */
    @PostMapping("/")
    public Long newCourse(CourseEntity courseEntity){
        courseService.newCourse(courseEntity);
        return courseEntity.getId();
    }
}
