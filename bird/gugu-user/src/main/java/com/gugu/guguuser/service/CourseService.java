package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.pojo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    @Autowired
    CourseDaoImpl courseDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    @Autowired
    SeminarScoreDao seminarScoreDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    KlassDaoImpl klassDao;

    /**
     * 获取与学生相关的基本课程信息
     * @param studentId
     * @return
     */
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId) {
        return courseDao.findSimpleCourseEntityByStudentId(studentId);
    }

    /**
     * 新建课程
     * @param courseEntity
     * @return
     */
    public Long newCourse(CourseEntity courseEntity){
        return courseDao.newCourse(courseEntity);
    }

    /**
     * 根据courseId获取课程详细信息
     * @param id
     * @return
     */
    public CourseEntity getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    /**
     * 根据courseId删除课程
     * @param id
     * @throws Exception
     */
    public void deleteCourseById(Long id) throws Exception {
        courseDao.deleteCourseById(id);
        ArrayList<KlassEntity> klassEntities=klassDao.getKlassByCourseId(id);
        for(int i=0;i<klassEntities.size();i++){
            klassDao.deleteKlassById(klassEntities.get(i).getId());
        }
    }

    /**
     * 获取小组的所有成绩记录
     * @param student_id
     * @param course_id
     * @return
     */
    public ArrayList<SeminarScoreEntity> getTeamAllScore(Long student_id,Long course_id) {
        Long team_id=klassStudentDao.getTeamId(student_id,course_id);
        return seminarScoreDao.getTeamAllScore(team_id);
    }

    /**
     * 通过teamid获取小组信息
     * @param teamId
     * @return
     */
    public TeamEntity getTeamById(Long teamId) {
        return teamDao.getTeamById(teamId);
    }

    /**
     * 获取课程下的班级信息
     * @param courseId
     * @return
     */
    public ArrayList<KlassEntity> getKlassByCourseId(Long courseId) {
        return klassDao.getKlassByCourseId(courseId);
    }

    /**
     * 获取课程相关的所有共享关系
     * @param courseId
     * @return
     */
    public ArrayList<ShareMessageEntity> getAllShare(Long courseId) {
        ArrayList<ShareMessageEntity> shareMessageEntities=courseDao.getSeminarShareMessage(courseId);
        shareMessageEntities.addAll(courseDao.getTeamShareMessage(courseId));
        return shareMessageEntities;
    }

    /**
     * 删除分享关系，并删除从课程的部分数据
     * @param shareId
     * @param type
     * @return
     */
    public boolean deleteCourseShare(Long shareId,Integer type){
        if(type.equals(1)){
            return courseDao.deleteSeminarShare(shareId);
        }else{
            return courseDao.deleteTeamShare(shareId);
        }
    }
}
