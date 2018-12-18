package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.mapper.TeacherMapper;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;

import java.util.List;


import java.util.ArrayList;

/**
 * @author ljy
 */
@Component
public class StudentDao {
    @Autowired
    StudentMapper studentMapper;

    /**
     * 管理员根据学生ID删除学生账号
     * @param id
     * @return
     */
    public void deleteStudentById(Long id){
        studentMapper.deleteStudentById(id);
    }


    /**
     * 获取除了队长以外其他成员的信息
     * @param teamId
     * @return
     */
    public ArrayList<StudentEntity> getMembersExceptLeader(Long teamId) {
        ArrayList<StudentEntity> members=studentMapper.getMembers(teamId);
        StudentEntity leader=studentMapper.getLeader(teamId);
        members.remove(leader);
        return members;
    }

    /**
     * 获取队长的信息
     * @param teamId
     * @return
     */
    public StudentEntity getLeader(Long teamId) {
        return studentMapper.getLeader(teamId);
    }


    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        ArrayList<StudentEntity> studentEntities=studentMapper.getStudentWithoutTeam(courseId);
        StudentEntity studentEntity=studentMapper.getStudentById(studentId);
        studentEntities.remove(studentEntity);
        return studentEntities;
    }


    /**
     * @author TYJ
     * 管理员根据学生号或姓名搜索学生
     * @param identity
     * @return
     */
    public ArrayList<StudentEntity> searchStudent(String identity){
        return studentMapper.searchStudent(identity);
    }

    /**
     * @author ljy
     * 管理员获取所有学生账号信息
     * @param
     * @return
     */
    public ArrayList<StudentEntity> getStudents(){
       return studentMapper.getStudents();
    }

    /**
     * @author ljy
     * 管理员重置学生密码
     * @param studentId
     */
    public void resetStudentPassword(Long studentId){
        studentMapper.resetStudentPassword(studentId);
    }

    /**
     * @author ljy
     * 管理员修改学生信息，包括账号，姓名，邮箱
     * @param studentEntity
     */
    public void changeStudentInformation(StudentEntity studentEntity){
        studentMapper.changeStudentInformation(studentEntity);
    }

    public StudentEntity getStudentById(Long studentId){
        return studentMapper.getStudentById(studentId);
    }

    public void changePassword(String password,Long studentId){
        studentMapper.changePassword(password,studentId);
    }

    public void changeEmail(String email,Long studentId){
        studentMapper.changeEmail(email,studentId);
    }
}
