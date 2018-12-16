package com.gugu.guguadmin.service;

/**
 * @author ljy
 */
public interface StudentService {

    /**
     * 管理员根据学生ID删除学生账号
     * @param id
     * @return
     */
    void deleteStudentById(long id);

}
