package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import com.gugu.guguuser.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class StudentServiceImpl {
    @Autowired
    StudentDaoImpl studentDao;
    @Autowired
    KlassStudentDaoImpl klassStudentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    EmailUtil emailUtil;

    public ArrayList<StudentEntity> getMembers(Long teamId) {
        return studentDao.getMembersExceptLeader(teamId);
    }


    public StudentEntity getLeader(Long teamId) {
        return studentDao.getLeader(teamId);
    }


    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        return studentDao.getStudentWithoutTeamInCourse(courseId,studentId);
    }

    public Long getTeamId(Long courseId,Long studentId){
        return klassStudentDao.getTeamId(studentId,courseId);
    }

}
