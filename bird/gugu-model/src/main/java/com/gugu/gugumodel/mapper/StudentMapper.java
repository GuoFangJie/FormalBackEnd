package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ljy
 */
@Mapper
@Repository
public interface StudentMapper {

     /**
      * 管理员根据学生ID删除学生账号
      * @param id
      * @return
      */
     void deleteStudentById(long id);

     /**
      * 根据学号或姓名搜索学生列表
      * @param identity
      * @return
      */
     ArrayList<StudentEntity> searchStudent(String identity);

     /**
      * 获取小组成员列表
      * @param teamId
      * @return
      */
     ArrayList<StudentEntity> getMembers(Long teamId);

     /**
      * 获取小组组长信息
      * @param teamId
      * @return
      */
     StudentEntity getLeader(Long teamId);

     /**
      * 获取课程内未参加组队的同学
      * @param courseId
      * @return
      */
     ArrayList<StudentEntity> getStudentWithoutTeam(Long courseId);

     /**
      * 根据学生id获取学生信息
      * @param studentId
      * @return
      */
     StudentEntity getStudentById(Long studentId);

     /**
      * 获取所有的学生
      * @return
      */
     ArrayList<StudentEntity> getStudents();

     /**
      * 重置学生密码为初始密码
      * @param studentId
      */
     void resetStudentPassword(Long studentId);

     /**
      * 修改学生的信息
      * @param studentEntity
      */
     void changeStudentInformation(StudentEntity studentEntity);

     /**
      * 修改学生密码
      * @param password
      * @param studentId
      */
     void changePassword(String password,Long studentId);

     /**
      * 修改学生邮箱
      * @param email
      * @param studentId
      */
     void changeEmail(String email,Long studentId);

     /**
      * 激活学生账号
      * @param studentEntity
      */
     void activeStudent(StudentEntity studentEntity);

     /**
      * 新建学生账号
      * @param studentEntity
      */
     void newStudent(StudentEntity studentEntity);

     /**
      * 根据账号获取学生id，在导入名单的时候查重
      * @param account
      * @return
      */
     Long getStudentByAccount(String account);

     /**
      * 根据id获取学生邮箱
      * @param studentId
      * @return String
      */
     String getEmailById(Long studentId);

     /**
      * 查询该学生是否在该班级下
      * @param courseId
      * @param studentId
      */
     ArrayList<Long> checkCourse(Long courseId,Long studentId);
}
