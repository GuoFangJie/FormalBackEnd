package com.gugu.guguuser.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ren
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    StudentService studentService;
    @Test
    public void getMembersTest(){
        System.out.println(studentService.getStudentWithoutTeamInCourse(1L,95L).size());
    }
}
