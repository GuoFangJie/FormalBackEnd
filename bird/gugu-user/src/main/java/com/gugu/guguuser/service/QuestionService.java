package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.AttendanceDao;
import com.gugu.gugumodel.dao.KlassStudentDao;
import com.gugu.gugumodel.dao.QuestionDao;
import com.gugu.gugumodel.entity.QuestionEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    AttendanceDao attendanceDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    /**
     * 获取下一个提问
     */
    public QuestionEntity getNext(Long attendanceId){
        return questionDao.getNext(attendanceId);
    }
    /**
     * 新建一个提问
     */
    public void newQuestion(Long userId,Long attendanceId){
        QuestionEntity questionEntity=new QuestionEntity();
        questionEntity.setAttendanceId(attendanceId);
        questionEntity.setStudentId(userId);
        questionEntity.setKlassSeminarId(attendanceDao.getById(attendanceId).getKlassSeminarId());
        questionEntity.setTeamId(klassStudentDao.getTeamIdByClassAndStudent(questionEntity.getKlassSeminarId(),userId));
        questionDao.newQuestion(questionEntity);
    }
    /**
     * 给提问打分
     */
    public void scoreQuestion(Long questionId,Float score) throws NotFoundException {
        questionDao.scoreQuestion(questionId,score);
    }
}
