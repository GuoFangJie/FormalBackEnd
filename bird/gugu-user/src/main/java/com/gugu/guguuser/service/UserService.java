package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.StudentDaoImpl;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import com.gugu.guguuser.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class UserService {
    @Autowired
    StudentDaoImpl studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    EmailUtil emailUtil;
    public boolean sendPasswordToUser(Long userId,String role){
        if(role.equals("Teacher")){
            TeacherEntity teacherEntity=teacherDao.getTeacherById(userId);
            emailUtil.sendSimpleEmail("讨论课管理平台找回密码","您的密码为"+teacherEntity.getPassword()+"  请妥善保管",teacherEntity.getEmail());
            return true;
        }else{
            StudentEntity studentEntity=studentDao.getStudentById(userId);
            emailUtil.sendSimpleEmail("讨论课管理平台找回密码","您的密码为"+studentEntity.getPassword()+"  请妥善保管",studentEntity.getEmail());
            return true;
        }
    }

    public boolean changePassword(String role,String password,Long userId){
        if(role.equals("Teacher")){
            teacherDao.changePassword(password,userId);
        }else{
            studentDao.changePassword(password,userId);
        }
        return true;
    }

    public StudentEntity getUserInfo(String role,Long userId){
        if(role.equals("Teacher")){
            return new StudentEntity(teacherDao.getTeacherById(userId));
        }else{
            return studentDao.getStudentById(userId);
        }
    }

    public boolean changeEmail(String role,String email,Long userId){
        if(role.equals("Teacher")){
            teacherDao.changeEmail(email,userId);
        }else{
            studentDao.changeEmail(email,userId);
        }
        return true;
    }
}
