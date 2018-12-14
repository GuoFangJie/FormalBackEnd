package com.gugu.gugumodel.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDaoTest {
    @Autowired
    CourseDaoImpl courseDao;
    @Test
    public void getCourse(){
        courseDao.findSimpleCourseEntityByStudentId(1L);
    }
}
