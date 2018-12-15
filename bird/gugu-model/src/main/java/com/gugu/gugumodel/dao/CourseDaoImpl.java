package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
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

    @Override
    public CourseEntity getCourseById(Long id) {
        return courseMapper.getCourseById(id);
    }

    @Override
    public void deleteCourseById(Long id) throws Exception {
        if(getCourseById(id)!=null) {
            courseMapper.deleteCourseById(id);
        }else{
            throw new Exception("找不到该记录");
        }
    }

}
