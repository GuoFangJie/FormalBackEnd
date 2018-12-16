package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.StudentEntity;

import java.util.ArrayList;

/**
 * @author
 */
public interface StudentService {


    ArrayList<StudentEntity> getMembers(Long courseId,Long studentId);

    StudentEntity getLeader(Long courseId,Long studentId);

    ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId);

}
