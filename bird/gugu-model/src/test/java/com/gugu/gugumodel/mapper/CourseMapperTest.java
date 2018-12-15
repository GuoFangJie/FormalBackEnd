package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTest {
    @Autowired
    CourseMapper courseMapper;
    @Test
    public void findSimpleCourseEntityByStudenIdTest(){
        System.out.println(courseMapper.findSimpleCourseEntityByStudenId(1L).size());
    }

    @Test
    public void newCourseTest(){
        CourseEntity courseEntity=new CourseEntity();
        System.out.println(courseMapper.newCourse(courseEntity));
        System.out.println(courseEntity.getId());
    }
    @Test
    public void getCourseByIdTest(){
        System.out.println(courseMapper.getCourseById(1L).getCourseName());
    }

    @Test
    public void deleteCourseByIdTest(){
        courseMapper.deleteCourseById(18L);
    }
}
