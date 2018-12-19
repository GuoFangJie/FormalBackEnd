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
public class SeminarMapperTest {
    @Autowired
    SeminarMapper seminarMapper;
    @Test
    public void getSeminarTest(){
        seminarMapper.getSeminarsByRound(1L);
    }
}
