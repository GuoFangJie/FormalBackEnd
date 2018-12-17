package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.TeacherMapper;
import com.gugu.gugumodel.mapper.TeamMapper;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
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
    public void deleteTeacherById(long id){
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
    public void newTeacher(TeacherEntity teacherEntity){
        teacherMapper.newTeacher(teacherEntity);
    }

    /**
     * @author TYJ
     * 管理员根据教工号或姓名搜索教师
     * @param identity
     */
    public ArrayList<TeacherEntity> searchTeacher(String identity){
        return teacherMapper.searchTeacher(identity);
    }


    public TeacherEntity getTeacherById(Long teacherId){
        return teacherMapper.getTeacherById(teacherId);
    }

    public void changePassword(String password,Long teacherId){
        teacherMapper.changePassword(password,teacherId);
    }
    public void changeEmail(String email,Long teacherId){
        teacherMapper.changeEmail(email,teacherId);
    }
}
