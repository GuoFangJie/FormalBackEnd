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
    public void deleteStudentById(long id);

    ArrayList<StudentEntity> getMembersExceptLeader(Long teamId);

    StudentEntity getLeader(Long teamId);
}
