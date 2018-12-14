package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;

import java.util.ArrayList;

public interface CourseDao {
    ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId);

    Long newCourse(CourseEntity courseEntity);
}
