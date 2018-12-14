package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class CourseDaoImpl implements CourseDao {
    @Autowired
    CourseMapper courseMapper;
    @Override
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId) {
        return courseMapper.findSimpleCourseEntityByStudenId(studentId);
    }
    @Override
    public Long newCourse(CourseEntity courseEntity){
        return courseMapper.newCourse(courseEntity);
    }
}
