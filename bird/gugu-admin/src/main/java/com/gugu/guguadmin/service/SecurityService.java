package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
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
        return adminDao.adminLogin(account);
    }
}
