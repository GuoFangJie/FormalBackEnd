package com.gugu.gugumodel.mapper;

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
public class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;
    @Test
    public void getMembers(){
        System.out.println(studentMapper.getMembers(1L).size());
    }

    @Test
    public void getLeader(){
        System.out.println(studentMapper.getLeader(1L).getStudentName());
    }

    @Test
    public void getStudentWithoutTeamTest(){
        System.out.println(studentMapper.getStudentWithoutTeam(1L).size());
    }
}
