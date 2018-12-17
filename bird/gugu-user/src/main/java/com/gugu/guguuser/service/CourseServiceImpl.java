package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.pojo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDaoImpl courseDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    @Autowired
    SeminarScoreDao seminarScoreDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    KlassDao klassDao;
    @Override
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId) {
        return courseDao.findSimpleCourseEntityByStudentId(studentId);
    }

    @Override
    public Long newCourse(CourseEntity courseEntity){
        return courseDao.newCourse(courseEntity);
    }

    @Override
    public CourseEntity getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public void deleteCourseById(Long id) throws Exception {
        courseDao.deleteCourseById(id);
    }

    @Override
    public ArrayList<SeminarScoreEntity> getTeamAllScore(Long student_id,Long course_id) {
        Long team_id=klassStudentDao.getTeamId(student_id,course_id);
        return seminarScoreDao.getTeamAllScore(team_id);
    }
    @Override
    public TeamEntity getTeamById(Long studentId, Long courseId) {
        Long teamId=klassStudentDao.getTeamId(studentId,courseId);
        return teamDao.getTeamById(teamId);
    }

    @Override
    public ArrayList<KlassEntity> getKlassByCourseId(Long courseId) {
        return klassDao.getKlassByCourseId(courseId);
    }

    @Override
    public ArrayList<ShareMessageEntity> getAllShare(Long courseId) {
        ArrayList<ShareMessageEntity> shareMessageEntities=courseDao.getSeminarShareMessage(courseId);
        shareMessageEntities.addAll(courseDao.getTeamShareMessage(courseId));
        return shareMessageEntities;
    }

    public boolean deleteCourseShare(Long shareId,Integer type){
        if(type.equals(1)){
            return courseDao.deleteSeminarShare(shareId);
        }else{
            return courseDao.deleteTeamShare(shareId);
        }
    }
}
