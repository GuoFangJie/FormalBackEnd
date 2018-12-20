package com.gugu.gugumodel.entity;

/**
 * @author ren
 */
public class RoundScoreEntity {
    Long roundId;
    Long teamId;
    Float totalScore;
    Float presentationScore;
    Float questionScore;
    Float reportScore;

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
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

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Float getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Float presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Float getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Float questionScore) {
        this.questionScore = questionScore;
    }

    public Float getReportScore() {
        return reportScore;
    }

    public void setReportScore(Float reportScore) {
        this.reportScore = reportScore;
    }
}
