package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassStudentMapper;
import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * @author ljy
 */
@Component
public class StudentDao {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    /**
     * 管理员根据学生ID删除学生账号
     * @param id
     * @return
     */
    public void deleteStudentById(Long id){
        studentMapper.deleteStudentById(id);
    }

//    /**
//     * @author TYJ
//     * 删除学生账号要删除学生的组队情况
//     * @param studentId
//     */
//    public void existAllTeam(Long studentId){
//        studentMapper.existAllTeam(studentId);
//    }

    /**
     * 获取除了队长以外其他成员的信息
     * @param teamId
     * @return
     */
    public ArrayList<StudentEntity> getMembersExceptLeader(Long teamId) {
        ArrayList<StudentEntity> members=studentMapper.getMembers(teamId);
        StudentEntity leader=studentMapper.getLeader(teamId);
        members.remove(leader);
        System.out.println("队伍成员有"+members.size());
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


    /**
     * 获取未组队学生列表
     * @param courseId
     * @param studentId
     * @return
     */
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        //获取课程下所有学生
        ArrayList<StudentEntity> studentsInCourse=klassStudentMapper.getStudentInCourse(courseId);
        for(int i=0;i<studentsInCourse.size();i++){
            Long userId=studentsInCourse.get(i).getId();
            if(!studentId.equals(userId)&&klassStudentMapper.findTeamIdByStudentIdAndCourseId(userId,courseId)==null){
                studentsInCourse.remove(i);
                i--;
            }
        }
        //studentMapper.getStudentWithoutTeam(courseId);
        StudentEntity studentEntity=studentMapper.getStudentById(studentId);
        return studentsInCourse;
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

    /**
     * 根据id获取学生信息
     * @param studentId
     * @return
     */
    public StudentEntity getStudentById(Long studentId){
        return studentMapper.getStudentById(studentId);
    }

    /**
     * 修改密码
     * @param password
     * @param studentId
     */
    public void changePassword(String password,Long studentId){
        studentMapper.changePassword(password,studentId);
    }

    /**
     * 修改邮箱
     * @param email
     * @param studentId
     */
    public void changeEmail(String email,Long studentId){
        studentMapper.changeEmail(email,studentId);
    }

    /**
     * 激活学生账号
     * @param
     * @return
     */
    public boolean activeStudent(StudentEntity studentEntity){
        if(studentMapper.getStudentById(studentEntity.getId())==null){
            return false;
        }
        studentMapper.activeStudent(studentEntity);
        return true;
    }

    /**
     * 新建学生
     */
    public Long newStudent(StudentEntity studentEntity){
        studentMapper.newStudent(studentEntity);
        return studentEntity.getId();
    }

    public Long getStudentByAccount(String account){
        return studentMapper.getStudentByAccount(account);
    }


    /**改数据库后新修改部分
     * 根据id获取学生所在小组
     * @return
     */
    public Long getStudentTeam(Long studentId,Long classId){
        return klassStudentMapper.getStudentTeam(studentId);
    }
}
