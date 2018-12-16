package com.gugu.guguadmin.service;

import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;

import java.util.ArrayList;

/**
 * @author ljy
 */
public interface StudentService {

    /**@author ljy
     * 管理员根据学生ID删除学生账号
     * @param id
     */
    void deleteStudentById(Long id);

    /**
     * @author ljy
     * 管理员获取所有学生信息
     */
    ArrayList<StudentEntity> getStudents();

    /**@author ljy
     * 管理员重置学生密码
     * @param studentId
     */
    void resetStudentPassword(Long studentId);

    /**@author ljy
     * 管理员修改学生信息，包括账号，姓名，邮箱
     * @param studentEntity
     */
    void changeStudentInformation(StudentEntity studentEntity);
}
