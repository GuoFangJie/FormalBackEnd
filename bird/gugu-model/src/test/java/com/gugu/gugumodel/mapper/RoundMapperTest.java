package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.RoundEntity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ren
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundMapperTest {
    @Autowired
    RoundMapper roundMapper;
    public void test(){
        RoundEntity roundEntity=new RoundEntity();
        roundMapper.newRound(roundEntity);
        System.out.println(roundEntity.getId());
    }
}
