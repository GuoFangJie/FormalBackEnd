package com.gugu.gugumodel.dao;

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
    void deleteStudentById(long id);

    /**
     * 管理员获取所有学生账号信息
     * @param
     * @return
     */
    List<StudentBasicInforVO> studentInfor();

    ArrayList<StudentEntity> searchStudent(String identity);
}
