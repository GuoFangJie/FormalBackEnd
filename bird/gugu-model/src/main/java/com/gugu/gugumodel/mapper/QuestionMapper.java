package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
@Mapper
public interface QuestionMapper {
    /**
     * 新增一条提问的记录
     */
    void newQuestion(QuestionEntity questionEntity);

    /**
     * 获取下一个提问
     */
    QuestionEntity getNextQuestion(Long attendanceId);

    /**
     * 给提问打分
     */
    void scoreQuestion(Float score,Long questionId);

    /**
     * 获取提问
     */
    QuestionEntity getQuestionById(Long questionId);

    /**
     * 获取提问列表
     */
    ArrayList<QuestionEntity> getAllQuestionByAttendanceId(Long attendanceId);
}
