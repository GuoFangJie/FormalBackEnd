package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ljy
 */

@Mapper
@Repository
public interface TeacherMapper{
        /**
         * 根据老师id删除老师账号
         * @param id
         */
        void deleteTeacherById(long id);

        /**
         * 获取所有的老师的账号
         * @return
         */
        ArrayList<TeacherEntity> getTeachers();

        /**
         * 重置老师密码为初始密码
         * @param teacherId
         */
        void resetTeacherPassword(Long teacherId);

        /**
         * 修改老师的信息
         * @param teacherEntity
         */
        void changeTeacherInformation(TeacherEntity teacherEntity);

        /**
         * 新建老师的账号
         * @param teacherEntity
         * @return
         */
        Long newTeacher(TeacherEntity teacherEntity);

        /**
         * 根据老师id获取老师的信息
         * @param teacherId
         * @return
         */
        TeacherEntity getTeacherById(Long teacherId);

        /**
         * 修改老师的密码
         * @param password
         * @param teacherId
         */
        void changePassword(String password,Long teacherId);

        /**
         * 修改老师的邮箱
         * @param email
         * @param teacherId
         */
        void changeEmail(String email,Long teacherId);

        /**
         * 管理员重置教师密码
         * @param identity
         * @return ArrayList
         */
        ArrayList<TeacherEntity> searchTeacher(String identity);

        /**
         * 激活老师账号
         */
        void activeTeacher(TeacherEntity teacherEntity);

        /**
         * 根据账号获取老师id
         * @param account
         * @return
         */
        Long getTeacherByAccount(String account);
}
