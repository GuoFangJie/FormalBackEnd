package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDaoImpl;
import com.gugu.gugumodel.dao.KlassStudentDaoImpl;
import com.gugu.gugumodel.dao.SeminarScoreDaoImpl;
import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SeminarScoreEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDaoImpl courseDao;
    @Autowired
    KlassStudentDaoImpl klassStudentDao;
    @Autowired
    SeminarScoreDaoImpl seminarScoreDao;
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
}
