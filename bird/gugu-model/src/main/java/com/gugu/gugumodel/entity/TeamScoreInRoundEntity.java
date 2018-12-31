package com.gugu.gugumodel.entity;

import java.util.ArrayList;

/**
 * 一个team在一轮中的所有成绩
 * @author ren
 */
public class TeamScoreInRoundEntity {
    private TeamEntity teamEntity;
    private RoundScoreEntity roundScoreEntity;
    private ArrayList<SeminarScoreEntity> seminarScoreEntities;


    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public RoundScoreEntity getRoundScoreEntity() {
        return roundScoreEntity;
    }

    public void setRoundScoreEntity(RoundScoreEntity roundScoreEntity) {
        this.roundScoreEntity = roundScoreEntity;
    }

    public ArrayList<SeminarScoreEntity> getSeminarScoreEntities() {
        return seminarScoreEntities;
    }

    public void setSeminarScoreEntities(ArrayList<SeminarScoreEntity> seminarScoreEntities) {
        this.seminarScoreEntities = seminarScoreEntities;
    }
}
