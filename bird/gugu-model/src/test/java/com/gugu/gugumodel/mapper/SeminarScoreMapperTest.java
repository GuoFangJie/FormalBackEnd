package com.gugu.gugumodel.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeminarScoreMapperTest {
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    @Test
    public void getTeamAllScoreTest(){
        System.out.println(seminarScoreMapper.getTeamAllScore(1L).size());
    }
}
