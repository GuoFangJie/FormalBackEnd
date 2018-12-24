package com.gugu.gugumodel.dao;

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
public class QuestionDaoTest {
    @Autowired
    QuestionDao questionDao;
    @Test
    public void test(){
        System.out.println(questionDao.getNext(3L).getId());
    }
}
