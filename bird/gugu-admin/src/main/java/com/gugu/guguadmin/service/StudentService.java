package com.gugu.guguadmin.service;

import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;

import java.util.List;

/**
 * @author ljy
 */
public interface StudentService {

    /**
     * 管理员根据学生ID删除学生账号
     * @param id
     * @return
     */
    public void deleteStudentById(long id);

    /**
     * 管理员获取所有学生信息并展示
     * @param
     * @return
     */
    List<StudentBasicInforVO> studentInfor();
}
