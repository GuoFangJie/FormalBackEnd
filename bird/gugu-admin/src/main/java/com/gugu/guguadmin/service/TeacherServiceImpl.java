package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ljy
 */

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;

    @Override
    public void deleteTeacherById(long id){
        teacherDao.deleteTeacherById(id);
    }
}
