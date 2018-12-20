package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.KlassEntity;
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
public class KlassMapperTest {
    @Autowired
    KlassMapper klassMapper;
    @Test
    public void getKlassTest(){
        System.out.println(klassMapper.getKlassByCourseId(1L).size());
    }

    @Test
    public void newKlassTest(){
        KlassEntity klassEntity=new KlassEntity();
        klassMapper.newKlass(klassEntity);
    }
    @Test
    public void getClassTest(){
        klassMapper.getKlassById(1L);
    }
}
