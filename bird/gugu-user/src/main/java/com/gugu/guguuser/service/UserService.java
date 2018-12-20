package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeacherEntity;
import com.gugu.guguuser.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class UserService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    EmailUtil emailUtil;

    /**
     * 发送密码到邮箱
     * @param userId
     * @param role
     * @return
     */
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

    /**
     * 修改密码
     * @param role
     * @param password
     * @param userId
     * @return
     */
    public boolean changePassword(String role,String password,Long userId){
        if(role.equals("Teacher")){
            teacherDao.changePassword(password,userId);
        }else{
            studentDao.changePassword(password,userId);
        }
        return true;
    }

    /**
     * 获取个人信息
     * @param role
     * @param userId
     * @return
     */
    public Object getUserInfo(String role,Long userId){
        if(role.equals("Teacher")){
            return teacherDao.getTeacherById(userId);
        }else{
            return studentDao.getStudentById(userId);
        }
    }

    /**
     * 修改邮箱
     * @param role
     * @param email
     * @param userId
     * @return
     */
    public boolean changeEmail(String role,String email,Long userId){
        if(role.equals("Teacher")){
            teacherDao.changeEmail(email,userId);
        }else{
            studentDao.changeEmail(email,userId);
        }
        return true;
    }
}
