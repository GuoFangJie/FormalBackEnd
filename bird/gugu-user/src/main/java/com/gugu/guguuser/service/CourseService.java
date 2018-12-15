package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SeminarScoreEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author ren
 */
public interface CourseService {
    ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId);

    Long newCourse(CourseEntity courseEntity);

    CourseEntity getCourseById(Long id);

    void deleteCourseById(Long id) throws Exception;

    ArrayList<SeminarScoreEntity> getTeamAllScore(Long student_id,Long course_id);
}
