package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.*;

import java.util.ArrayList;

/**
 * @author ljy
 */
public interface TeacherDao {

    /**
     * 管理员根据教师ID删除教师账号
     * @param id
     * @return
     */
    void deleteTeacherById(long id);

    /**
     * 管理员获取所有教师信息
     * @return ArrayList
     */
    ArrayList<TeacherEntity> getTeachers();
}
