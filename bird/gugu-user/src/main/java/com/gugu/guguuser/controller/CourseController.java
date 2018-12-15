package com.gugu.guguuser.controller;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SeminarScoreEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import com.gugu.gugumodel.pojo.vo.UserAccountVO;
import com.gugu.guguuser.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 根据课程id获取课程详细信息
     * @param id
     * @return
     */
    @GetMapping("/{courseId}")
    public CourseEntity getCourseById(@PathVariable("courseId") Long id){
        return courseService.getCourseById(id);
    }

    /**
     * 根据课程id删除课程，若不存在该课程返回404
     * @param id
     * @param httpServletResponse
     */
    @DeleteMapping("/{courseId}")
    public void deleteCourseById(@PathVariable("courseId") Long id, HttpServletResponse httpServletResponse){
        try {
            courseService.deleteCourseById(id);
        }catch (Exception e){
            httpServletResponse.setStatus(404,e.getMessage());
        }
    }

    /**
     * 获取小组所有讨论课成绩
     * @param courseId
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/{courseId}/score")
    public ArrayList<SeminarScoreEntity> getTeamScoreAll(@PathVariable("courseId") Long courseId, HttpServletRequest httpServletRequest){
        Long student_id=Long.parseLong(httpServletRequest.getAttribute("student_id").toString());
        return courseService.getTeamAllScore(student_id,courseId);
    }
}
