package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import com.gugu.gugumodel.entity.ShareReceiveCourseEntity;
import com.gugu.gugumodel.entity.SimpleCourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Mapper
@Repository
public interface CourseMapper {
     ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudenId(Long studentId);

     ArrayList<SimpleCourseEntity> findSimpleCourseEntityByTeacherId(Long teacherId);
     Long newCourse(CourseEntity courseEntity);


     CourseEntity getCourseById(Long id);

     void deleteCourseById(Long id);

     Long getTeamMainCourseId(Long courseId);

     Long getSeminarMainCourseId(Long courseId);

     ArrayList<ShareReceiveCourseEntity> getTeamRecieveCourses(Long teamMainCourseId);

     ArrayList<ShareReceiveCourseEntity> getSeminarRecieveCourses(Long seminarMainCourseId);

     SimpleCourseEntity getSimpleCourseById(Long courseId);

     Long getShareSeminarIdByCourse(Long mainCourseId,Long subCourseId);

     Long getShareTeamIdByCourse(Long mainCourseId,Long subCourseId);

     ArrayList<Long> getCourseIdByTeacherId(long id);

     void deleteSeminarShare(Long id);

     void deleteTeamShare(Long id);

     void deleteCourseTeamMain(Long id);

     void deleteCourseSeminarMain(Long id);

     ShareApplicationEntity getSeminarShareApplicationById(Long shareId);

     ShareApplicationEntity getTeamShareApplicationById(Long shareId);

     void deleteAllTeamByCourseId(Long courseId);

     void deleteAllSeminarByCourseId(Long seminarId);

     Long getTeacherIdByCourse(Long courseId);

     /**
      * @author TYJ
      * 修改课程共享讨论课的状态
      * @param subCourseId
      * @param mainCourseId
      * @return
      */
     int changeSeminarShareStatus(Long subCourseId,Long mainCourseId);
}
