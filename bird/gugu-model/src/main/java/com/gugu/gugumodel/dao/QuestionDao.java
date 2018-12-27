package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.QuestionEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.QuestionMapper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class QuestionDao {
    @Autowired
    QuestionMapper questionMapper;
    /**
     * 给提问打分
     */
    public void scoreQuestion(Long questionId,Float score) throws NotFoundException {
        if(questionMapper.getQuestionById(questionId)==null){
            throw new NotFoundException("记录不存在");
        }else{
            questionMapper.scoreQuestion(score,questionId);
        }
    }

    /**
     * 获取下一个提问
     */
    public QuestionEntity getNext(Long attendanceId){
        return questionMapper.getNextQuestion(attendanceId);
    }
    /**
     * 新建提问数据
     */
    public void newQuestion(QuestionEntity questionEntity){
        questionMapper.newQuestion(questionEntity);
    }
    /**
     * 获取当前提问列表
     */
    public ArrayList<QuestionEntity> getAllQuestionByAttendanceId(Long attendanceId){
        return questionMapper.getAllQuestionByAttendanceId(attendanceId);
    }
}
