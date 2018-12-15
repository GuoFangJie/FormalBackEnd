package com.gugu.gugumodel.pojo.entity;

/**
 * 讨论课成绩的实体类
 * @author ren
 */
public class SeminarScoreEntity {
    Long klass_seminar_id;
    Long team_id;
    float total_score;
    float presentation_score;
    float question_score;
    float report_score;

    public Long getKlass_seminar_id() {
        return klass_seminar_id;
    }

    public void setKlass_seminar_id(Long klass_seminar_id) {
        this.klass_seminar_id = klass_seminar_id;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public float getTotal_score() {
        return total_score;
    }

    public void setTotal_score(float total_score) {
        this.total_score = total_score;
    }

    public float getPresentation_score() {
        return presentation_score;
    }

    public void setPresentation_score(float presentation_score) {
        this.presentation_score = presentation_score;
    }

    public float getQuestion_score() {
        return question_score;
    }

    public void setQuestion_score(float question_score) {
        this.question_score = question_score;
    }

    public float getReport_score() {
        return report_score;
    }

    public void setReport_score(float report_score) {
        this.report_score = report_score;
    }
}
