package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDao;
import com.gugu.gugumodel.entity.CourseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseDao courseDao;
    @Autowired
    KlassService klassService;
    @Test
    public void findSimpleCourseTest(){
        System.out.println(klassService.getKlassIdByCourseAndStudent(30L,414L));
    }
    @Test
    public void newCourseTest(){
        CourseEntity courseEntity=new CourseEntity();
        System.out.println(courseDao.newCourse(courseEntity));
    }
    @Test
    public void getCourseById(){
        courseService.getCourseById(18L);
    }
}
