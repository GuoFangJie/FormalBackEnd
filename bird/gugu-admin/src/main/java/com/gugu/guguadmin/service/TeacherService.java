package com.gugu.guguadmin.service;

import com.gugu.gugumodel.pojo.entity.*;

import java.util.ArrayList;

/**
 * @author ljy
 */
public interface TeacherService {

    /**
     * 管理员根据教师ID删除教师账号
     * @param id
     * @return
     */
    void deleteTeacherById(long id)throws Exception;

    /**
     * 管理员获取所有教师信息
     * @return ArrayList
     */
    ArrayList<TeacherEntity> getTeachers();


    /**@author ljy
     * 管理员重置教师密码
     * @param teacherId
     */
    void resetTeacherPassword(Long teacherId);

    /**@author ljy
     * 管理员修改教师信息，包括账号，姓名，邮箱
     * @param teacherEntity
     */
    void changeTeacherInformation(TeacherEntity teacherEntity);

    /**@author ljy
     * 管理员新建教师账号
     * @param teacherEntity
     * @return
     */
    void newTeacher(TeacherEntity teacherEntity);
}
