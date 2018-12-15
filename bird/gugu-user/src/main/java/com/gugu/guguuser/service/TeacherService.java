package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SeminarScoreEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;

import java.util.ArrayList;

/**
 * @author ljy
 */
public interface TeacherService {

    /**
     * 管理员根据教师ID删除教师账号
     * @param id
     * @return
     */
    public void deleteTeacherById(long id);

}
