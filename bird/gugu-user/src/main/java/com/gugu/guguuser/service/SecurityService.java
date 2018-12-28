package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.entity.SecurityUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ren
 * security登录的部分
 */
@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Long studentId=studentDao.getStudentByAccount(s);
        if(studentId!=null){
            return new SecurityUserEntity(s,studentDao.getStudentById(studentId).getPassword(),"ROLE_Student");
        }else if(teacherDao.getTeacherByAccount(s)!=null){
            Long teacherId=teacherDao.getTeacherByAccount(s);
            return new SecurityUserEntity(s,teacherDao.getTeacherById(teacherId).getPassword(),"ROLE_Teacher");
        }else{
            throw  new UsernameNotFoundException("不存在该用户");
        }
    }
}
