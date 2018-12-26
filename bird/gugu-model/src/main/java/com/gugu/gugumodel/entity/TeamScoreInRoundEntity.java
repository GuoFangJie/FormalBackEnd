package com.gugu.gugumodel.entity;

import java.util.ArrayList;

/**
 * 一个team在一轮中的所有成绩
 * @author ren
 */
public class TeamScoreInRoundEntity {
    //小组在一轮讨论课中的总成绩,其中包含teamid和roundid
    RoundScoreEntity roundScoreEntity;
    //在一轮中的每次讨论课的成绩
    ArrayList<SeminarScoreEntity> seminarScoreEntities;


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
