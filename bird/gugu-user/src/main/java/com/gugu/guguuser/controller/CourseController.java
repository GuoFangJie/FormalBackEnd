package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.*;
import com.gugu.guguuser.controller.vo.TeamMessageVO;
import com.gugu.guguuser.service.CourseService;
import com.gugu.guguuser.service.KlassService;
import com.gugu.guguuser.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    KlassService klassService;
    /**
     * 获取与用户相关的课程
     * @param
     * @return
     */
    @GetMapping("")
    public ArrayList<SimpleCourseEntity> getCourseByUser(HttpServletRequest httpServletRequest){
        String userId=httpServletRequest.getAttribute("userId").toString();
        return courseService.findSimpleCourseEntityByStudentId(Long.parseLong(userId));
    }

    /**
     * 新建课程，返回课程id
     * @param courseEntity
     * @return
     */
    @PostMapping("")
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
    public boolean deleteCourseById(@PathVariable("courseId") Long id, HttpServletResponse httpServletResponse){
        try {
            courseService.deleteCourseById(id);
        }catch (Exception e){
            httpServletResponse.setStatus(404,e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取小组所有讨论课成绩
     * @param courseId
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/{courseId}/score")
    public ArrayList<SeminarScoreEntity> getTeamScoreAll(@PathVariable("courseId") Long courseId, HttpServletRequest httpServletRequest){
        Long student_id=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return courseService.getTeamAllScore(student_id,courseId);
    }

    /**
     * 获取小组的详细信息
     * @param courseId
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/{courseId}/team")
    public TeamMessageVO getTeamMessage(@PathVariable("courseId") Long courseId,HttpServletRequest httpServletRequest){
        Long studentId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        Long teamId=studentService.getTeamId(courseId,studentId);
        return new TeamMessageVO(courseService.getTeamById(teamId),studentService.getLeader(teamId),studentService.getMembers(teamId));
    }

    /**
     * 获取同一个课程下面未组队的同学，不包括自己
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/noTeam")
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(@PathVariable("courseId") Long courseId, HttpServletRequest httpServletRequest){
        Long studentId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return studentService.getStudentWithoutTeamInCourse(courseId,studentId);
    }

    /**
     * 获取课程下的班级信息
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/class")
    public ArrayList<KlassEntity> getKlassByCourseId(@PathVariable("courseId") Long courseId){
        return courseService.getKlassByCourseId(courseId);
    }

    /**
     * 在课程下新建班级并可以导入学生名单（后半部分还没完成）
     * @param courseId
     * @param klassEntity
     * @return
     */
    @PostMapping("/{courseId}/class")
    public Long newKlass(@PathVariable("courseId") Long courseId,@RequestBody KlassEntity klassEntity){
        klassEntity.setCourseId(courseId);
        return klassService.newKlass(klassEntity);
    }

    /**
     * 获取该课程所有相关的共享信息
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/share")
    public ArrayList<ShareMessageEntity> getAllShareMessage(@PathVariable("courseId") Long courseId){
        return courseService.getAllShare(courseId);
    }

    /**
     * 删除共享关系
     * 1 为讨论课 2 为小组
     * @param shareId
     * @param type
     * @return
     */
//    @DeleteMapping("/{courseId}/share/{shareId}")
//    public boolean deleteCourseShare(@PathVariable("shareId") Long shareId,Integer type){
//        return courseService.deleteCourseShare(shareId,type);
//    }

}
