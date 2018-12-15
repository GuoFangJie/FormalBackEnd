package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ljy
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    @Override
    public void deleteStudentById(long id){
        studentDao.deleteStudentById(id);
    }

}
