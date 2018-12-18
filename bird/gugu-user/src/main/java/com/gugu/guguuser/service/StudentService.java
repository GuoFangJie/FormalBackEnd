package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.vo.ActiveUserVO;
import com.gugu.guguuser.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    KlassStudentDaoImpl klassStudentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    EmailUtil emailUtil;

    /**
     * 获取小组成员除了组长
     * @param teamId
     * @return
     */
    public ArrayList<StudentEntity> getMembers(Long teamId) {
        return studentDao.getMembersExceptLeader(teamId);
    }

    /**
     * 获取组长的数据
     * @param teamId
     * @return
     */
    public StudentEntity getLeader(Long teamId) {
        return studentDao.getLeader(teamId);
    }

    /**
     * 获取除了自己之外的未组队同学
     * @param courseId
     * @param studentId
     * @return
     */
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId) {
        return studentDao.getStudentWithoutTeamInCourse(courseId,studentId);
    }

    /**
     * 获取自己在当前课程下的小组id
     * @param courseId
     * @param studentId
     * @return
     */
    public Long getTeamId(Long courseId,Long studentId){
        return klassStudentDao.getTeamId(studentId,courseId);
    }

    /**
     * 激活学生账号
     */
    public boolean activeStudent(ActiveUserVO activeUserVO){
        return studentDao.activeStudent(activeUserVO);
    }

}
