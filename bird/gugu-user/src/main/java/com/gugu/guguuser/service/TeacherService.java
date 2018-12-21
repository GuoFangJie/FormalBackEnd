package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;

    /**
     * 激活老师账号
     * @param
     * @return
     */
    public boolean activeTeacher(TeacherEntity teacherEntity){
        return teacherDao.activeTeacher(teacherEntity);
    }

    /**
     * 根据账号获取id
     */
    public Long getIdByAccount(String account){
        return teacherDao.getTeacherByAccount(account);
    }
}
