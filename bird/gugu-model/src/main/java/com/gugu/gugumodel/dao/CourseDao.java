package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.ShareMessageEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public interface CourseDao {
    ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId);

    Long newCourse(CourseEntity courseEntity);

    CourseEntity getCourseById(Long id);

    void deleteCourseById(Long id) throws Exception;

    ArrayList<ShareMessageEntity> getSeminarShareMessage(Long courseId);

    ArrayList<ShareMessageEntity> getTeamShareMessage(Long courseId);


    /**根据教师id获取教师的所有课程
     * @author ljy
     * @param id
     * @return
     */
    ArrayList<Long> getCourseIdByTeacherId(long id);
}
