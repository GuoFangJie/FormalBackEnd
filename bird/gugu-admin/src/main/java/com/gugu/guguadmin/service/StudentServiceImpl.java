package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ljy
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public void deleteStudentById(Long id){
        studentDao.deleteStudentById(id);
    }

    @Override
    public ArrayList<StudentEntity> getStudents(){
        return studentDao.getStudents();
    }

    @Override
    public void resetStudentPassword(Long studentId){
        studentDao.resetStudentPassword(studentId);
    }

    @Override
    public void changeStudentInformation(StudentEntity studentEntity){
        studentDao.changeStudentInformation(studentEntity);
    }

}
