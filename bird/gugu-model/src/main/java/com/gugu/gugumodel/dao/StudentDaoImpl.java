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
public class StudentDaoImpl implements StudentDao {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public void deleteStudentById(long id){
        studentMapper.deleteStudentById(id);
    }

    @Override
    public ArrayList<StudentEntity> getMembersExceptLeader(Long teamId) {
        ArrayList<StudentEntity> members=studentMapper.getMembers(teamId);
        StudentEntity leader=studentMapper.getLeader(teamId);
        members.remove(leader);
        return members;
    }

    @Override
    public StudentEntity getLeader(Long teamId) {
        return studentMapper.getLeader(teamId);
    }

    @Override
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        ArrayList<StudentEntity> studentEntities=studentMapper.getStudentWithoutTeam(courseId);
        StudentEntity studentEntity=studentMapper.getStudentById(studentId);
        studentEntities.remove(studentEntity);
        return studentEntities;
    }


    @Override
    public ArrayList<StudentEntity> searchStudent(String identity){
        return studentMapper.searchStudent(identity);
    }

    @Override
    public List<StudentBasicInforVO> studentInfor(){
       return studentMapper.studentInfor();
    }
}
