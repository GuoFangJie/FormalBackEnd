package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.StudentEntity;

import java.util.ArrayList;

/**
 * @author
 */
public interface StudentService {


    ArrayList<StudentEntity> getMembers(Long courseId,Long studentId);


}
