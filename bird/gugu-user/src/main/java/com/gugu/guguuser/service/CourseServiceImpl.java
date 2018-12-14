package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDaoImpl;
import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDaoImpl courseDao;
    @Override
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId) {
        return courseDao.findSimpleCourseEntityByStudentId(studentId);
    }

    @Override
    public Long newCourse(CourseEntity courseEntity){
        return courseDao.newCourse(courseEntity);
    }
}
