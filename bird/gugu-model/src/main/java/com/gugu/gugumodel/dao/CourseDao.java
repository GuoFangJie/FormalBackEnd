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
}
