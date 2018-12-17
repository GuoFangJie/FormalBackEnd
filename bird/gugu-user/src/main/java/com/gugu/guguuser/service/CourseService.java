package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author ren
 */
public interface CourseService {
    ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId);

    Long newCourse(CourseEntity courseEntity);

    CourseEntity getCourseById(Long id);

    void deleteCourseById(Long id) throws Exception;

    ArrayList<SeminarScoreEntity> getTeamAllScore(Long student_id,Long course_id);

    TeamEntity getTeamById(Long studentId, Long courseId);

    ArrayList<KlassEntity> getKlassByCourseId(Long courseId);

    ArrayList<ShareMessageEntity> getAllShare(Long courseId);
}
