package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.StudentEntity;

import java.util.ArrayList;

import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljy
 */
public interface StudentDao {

    /**
     * 管理员根据学生ID删除学生账号
     * @param id
     * @return
     */
    public void deleteStudentById(long id);

    /**
     * 管理员获取所有学生账号信息
     * @param
     * @return
     */
    List<StudentBasicInforVO> studentInfor();

    ArrayList<StudentEntity> searchStudent(String identity);

    /**
     * 获取除了队长以外其他成员的信息
     * @param teamId
     * @return
     */
    ArrayList<StudentEntity> getMembersExceptLeader(Long teamId);

    /**
     * 获取队长的信息
     * @param teamId
     * @return
     */
    StudentEntity getLeader(Long teamId);

    ArrayList<StudentEntity> getStudentWithoutTeamInCourse(Long courseId,Long studentId);

}
