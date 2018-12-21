package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.AdminDao;
import com.gugu.gugumodel.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecurityService implements UserDetailsService {
    @Autowired
    AdminDao adminDao;
    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        AdminEntity result=adminDao.adminLogin(account);
        if(result==null){
            throw new UsernameNotFoundException("账号或密码错误");
        }
        return result;
    }
}
