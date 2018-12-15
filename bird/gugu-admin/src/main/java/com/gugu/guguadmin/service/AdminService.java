package com.gugu.guguadmin.service;

import com.gugu.gugumodel.pojo.entity.AdminEntity;
import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Component
public interface AdminService {

    public AdminEntity adminLogin(AdminEntity adminEntity);

}
