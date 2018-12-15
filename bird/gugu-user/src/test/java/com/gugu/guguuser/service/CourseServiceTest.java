package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDaoImpl;
import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.pojo.entity.CourseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {
    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Autowired
    CourseDaoImpl courseDaoImpl;

    @Test
    public void findSimpleCourseTest(){
        courseDaoImpl.findSimpleCourseEntityByStudentId(1L);
    }
    @Test
    public void newCourseTest(){
        CourseEntity courseEntity=new CourseEntity();
        System.out.println(courseDaoImpl.newCourse(courseEntity));
    }
    @Test
    public void getCourseById(){
        courseServiceImpl.getCourseById(18L);
    }
}