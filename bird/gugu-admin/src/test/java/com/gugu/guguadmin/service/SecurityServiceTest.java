package com.gugu.guguadmin.service;

import com.gugu.gugumodel.mapper.AdminMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ren
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityServiceTest {
    @Autowired
    SecurityService securityService;
    @Autowired
    AdminMapper adminMapper;

}
