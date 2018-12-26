package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.QuestionEntity;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeamEntity;

/**
 * @author ren
 */
public class QuestionVO {
    QuestionEntity questionEntity;
    StudentEntity studentEntity;
    TeamEntity teamEntity;

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    @Override
    public String toString() {
        return "{\"questionId\":"+questionEntity.getId()+",\"studentId\":"+studentEntity.getAccount()+",\"studentName\":"+studentEntity.getStudentName()+",\"teamId\":"+questionEntity.getTeamId();
    }
}
