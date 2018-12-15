package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.StudentEntity;

import java.util.ArrayList;

/**
 * @author ljy
 */
public interface StudentService {

    /**
     * 管理员根据学生ID删除学生账号
     * @param id
     * @return
     */
    public void deleteStudentById(long id);

    ArrayList<StudentEntity> getMembers(Long teamId);

    StudentEntity getLeader(Long teamId);

}
