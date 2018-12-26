package com.gugu.gugumodel.entity;

/**
 * 讨论课成绩的实体类
 * @author ren
 */
public class SeminarScoreEntity {
    private Long klassSeminarId;
    private Long teamId;
    private Float totalScore;
    private Float presentationScore;
    private Float questionScore;
    private Float reportScore;
    private SeminarEntity seminarEntity;
    private TeamEntity teamEntity;

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public SeminarEntity getSeminarEntity() {
        return seminarEntity;
    }

    public void setSeminarEntity(SeminarEntity seminarEntity) {
        this.seminarEntity = seminarEntity;
    }

    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Long klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(float presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Float getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(float questionScore) {
        this.questionScore = questionScore;
    }

    public Float getReportScore() {
        return reportScore;
    }

    public void setReportScore(float reportScore) {
        this.reportScore = reportScore;
    }
}
