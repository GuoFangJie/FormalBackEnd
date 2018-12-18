package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ljy
 */

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    /**
     * @author ljy
     * 管理员根据学生ID删除学生账号
     * @param id
     */
    public void deleteStudentById(Long id){
        studentDao.deleteStudentById(id);
    }

    /**
     * @author ljy
     * 管理员获取所有学生信息
     */
    public ArrayList<StudentEntity> getStudents(){
        return studentDao.getStudents();
    }

    /**
     * @author ljy
     * 管理员重置学生密码
     * @param studentId
     */
    public void resetStudentPassword(Long studentId){
        studentDao.resetStudentPassword(studentId);
    }

    /**
     * @author ljy
     * 管理员修改学生信息，包括账号，姓名，邮箱
     * @param studentEntity
     */
    public void changeStudentInformation(StudentEntity studentEntity){
        studentDao.changeStudentInformation(studentEntity);
    }

}
