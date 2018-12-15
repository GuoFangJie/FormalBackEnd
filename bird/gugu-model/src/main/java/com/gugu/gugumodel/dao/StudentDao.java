package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.StudentEntity;

import java.util.ArrayList;

/**
 * @author ljy
 */
public interface StudentDao {

    /**
     * 管理员根据学生ID删除学生账号
     * @param id
     * @return
     */
    void deleteStudentById(long id);

    /**
     * 管理员根据学生或姓名查找学生账号
     * @param identity
     * @return ArrayList<StudentEntity>
     */
    ArrayList<StudentEntity> searchStudent(String identity);

}
