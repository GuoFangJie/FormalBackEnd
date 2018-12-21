package com.gugu.guguuser.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author ren
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    EmailUtil emailUtil;
    @Test
    public void emailTest(){
        String get="qwe1138318433@qq.com";
        emailUtil.sendSimpleEmail("通知","通知各位同学",get);
    }
}
