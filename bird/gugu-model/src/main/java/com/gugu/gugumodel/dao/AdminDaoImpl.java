package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.AdminMapper;
import com.gugu.gugumodel.pojo.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Component
public class AdminDaoImpl implements AdminDao {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminEntity adminLogin(AdminEntity adminEntity){
        return adminMapper.adminLogin(adminEntity);
    }
}
