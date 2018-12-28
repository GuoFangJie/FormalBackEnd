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
     /**
      * 根据学生id获取与该学生相关的基本课程信息
      * @param studentId
      * @return
      */
     ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudenId(Long studentId);

     /**
      * 根据老师id获取与该老师相关的基本课程信息
      * @param teacherId
      * @return
      */
     ArrayList<SimpleCourseEntity> findSimpleCourseEntityByTeacherId(Long teacherId);

     /**
      * 获取所有的课程
      * @return
      */
     ArrayList<CourseEntity> getAllCourse();

     /**
      * 创建课程
      * @param courseEntity
      * @return
      */
     Long newCourse(CourseEntity courseEntity);

     /**
      * 根据课程id获取课程信息
      * @param id
      * @return
      */
     CourseEntity getCourseById(Long id);

     /**
      * 根据id删除课程记录
      * @param id
      */
     void deleteCourseById(Long id);

     /**
      * 获取某课程的共享分组主课程
      * @param courseId
      * @return
      */
     Long getTeamMainCourseId(Long courseId);

     /**
      * 获取某课程的共享讨论课主课程
      * @param courseId
      * @return
      */
     Long getSeminarMainCourseId(Long courseId);

     /**
      * 获取某主课程的共享分组从课程们
      * @param teamMainCourseId
      * @return
      */
     ArrayList<ShareReceiveCourseEntity> getTeamRecieveCourses(Long teamMainCourseId);

     /**
      * 获取某主课程的共享讨论课的从课程们
      * @param seminarMainCourseId
      * @return
      */
     ArrayList<ShareReceiveCourseEntity> getSeminarRecieveCourses(Long seminarMainCourseId);

     /**
      * 根据课程id获取基本的课程信息
      * @param courseId
      * @return
      */
     SimpleCourseEntity getSimpleCourseById(Long courseId);

     /**
      * 根据主课程和从课程获取共享讨论课记录的id
      * @param mainCourseId
      * @param subCourseId
      * @return
      */
     Long getShareSeminarIdByCourse(Long mainCourseId,Long subCourseId);

     /**
      * 根据主课程和从课程获取共享分组关系的id
      * @param mainCourseId
      * @param subCourseId
      * @return
      */
     Long getShareTeamIdByCourse(Long mainCourseId,Long subCourseId);

     /**
      * 获取与某老师相关的课程id列表
      * @param id
      * @return
      */
     ArrayList<Long> getCourseIdByTeacherId(long id);

     /**
      * 根据共享记录的id，删除共享讨论课记录
      * @param id
      */
     void deleteSeminarShare(Long id);

     /**
      * 根据共享记录的id，删除共享分组记录
      * @param id
      */
     void deleteTeamShare(Long id);

     /**
      * 删除某课程的共享组队主课程
      * @param id
      */
     void deleteCourseTeamMain(Long id);

     /**
      * 删除某课程的共享讨论课主课程
      * @param id
      */
     void deleteCourseSeminarMain(Long id);

     /**
      * 根据共享id获取共享讨论课申请的id
      * @param shareId
      * @return
      */
     ShareApplicationEntity getSeminarShareApplicationById(Long shareId);

     /**
      * 根据共享id获取共享组队申请的列表
      * @param shareId
      * @return
      */
     ShareApplicationEntity getTeamShareApplicationById(Long shareId);


     /**
      * 删除某课程下所有的讨论课
      * @param seminarId
      */
     void deleteAllSeminarByCourseId(Long seminarId);

     /**
      * 根据课程id获取老师id
      * @param courseId
      * @return
      */
     Long getTeacherIdByCourse(Long courseId);

     /**
      * @author TYJ
      * 修改课程共享讨论课的状态
      * @param subCourseId
      * @param mainCourseId
      * @return
      */
     int changeSeminarShareStatus(Long subCourseId,Long mainCourseId);

     /**
      * @author TYJ
      * 修改课程共享分组的状态
      * @param subCourseId
      * @param mainCourseId
      * @return
      */
     int changeTeamShareStatus(Long subCourseId,Long mainCourseId);
}
