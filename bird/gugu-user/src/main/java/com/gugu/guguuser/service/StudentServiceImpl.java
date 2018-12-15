package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ljy
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    @Override
    public void deleteStudentById(long id){
        studentDao.deleteStudentById(id);
    }

    @Override
    public ArrayList<StudentEntity> getMembers(Long teamId) {
        return studentDao.getMembersExceptLeader(teamId);
    }

    @Override
    public StudentEntity getLeader(Long teamId) {
        return studentDao.getLeader(teamId);
    }

}
