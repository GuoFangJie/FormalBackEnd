package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.TeacherEntity;
import com.gugu.gugumodel.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ljy
 */
@Component
public class TeacherDao {
    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 管理员根据教师ID删除教师账号
     * @param id
     * @return
     */
    public void deleteTeacherById(long id) {
            teacherMapper.deleteTeacherById(id);
    }

    /**
     * 管理员获取所有教师信息
     * @return ArrayList
     */
    public ArrayList<TeacherEntity> getTeachers(){
        return teacherMapper.getTeachers();
    }

    /**
     * @author ljy
     * 管理员重置教师密码
     * @param teacherId
     */
    public void resetTeacherPassword(Long teacherId){
        teacherMapper.resetTeacherPassword(teacherId);
    }

    /**
     * @author ljy
     * 管理员修改教师信息，包括账号，姓名，邮箱
     * @param teacherEntity
     */
    public void changeTeacherInformation(TeacherEntity teacherEntity){
        teacherMapper.changeTeacherInformation(teacherEntity);
    }

    /**
     * @author ljy
     * 管理员新建教师账号
     * @param teacherEntity
     */
    public Long newTeacher(TeacherEntity teacherEntity){
        return teacherMapper.newTeacher(teacherEntity);
    }

    /**
     * @author TYJ
     * 管理员根据教工号或姓名搜索教师
     * @param identity
     */
    public ArrayList<TeacherEntity> searchTeacher(String identity){
        return teacherMapper.searchTeacher(identity);
    }

    /**
     * 根据id获取老师信息
     * @param teacherId
     * @return
     */
    public TeacherEntity getTeacherById(Long teacherId){
        return teacherMapper.getTeacherById(teacherId);
    }

    /**
     * 修改老师密码
     * @param password
     * @param teacherId
     */
    public void changePassword(String password,Long teacherId){
        teacherMapper.changePassword(password,teacherId);
    }

    /**
     * 修改邮箱
     * @param email
     * @param teacherId
     */
    public void changeEmail(String email,Long teacherId){
        teacherMapper.changeEmail(email,teacherId);
    }
    /**
     * 激活账号
     */
    public boolean activeTeacher(TeacherEntity teacherEntity){
        if(teacherMapper.getTeacherById(teacherEntity.getId())==null){
            return false;
        }
        teacherMapper.activeTeacher(teacherEntity);
        return true;
    }

    /**
     * 根据账号获取老师id
     * @param account
     * @return
     */
    public Long getTeacherByAccount(String account){
        return teacherMapper.getTeacherByAccount(account);
    }
}
