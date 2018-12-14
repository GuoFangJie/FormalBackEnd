package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author ren
 */
@Component
public interface CourseService {
    ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId);

    Long newCourse(CourseEntity courseEntity);
}
