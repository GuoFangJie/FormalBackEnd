package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.RoundEntity;
import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.entity.TeamEntity;

/**
 * @author ren
 */
public class RoundScoreMessageVO {
    RoundScoreEntity roundScoreEntity;
    TeamEntity teamEntity;
    RoundEntity roundEntity;

    public RoundEntity getRoundEntity() {
        return roundEntity;
    }

    public void setRoundEntity(RoundEntity roundEntity) {
        this.roundEntity = roundEntity;
    }

    public RoundScoreEntity getRoundScoreEntity() {
        return roundScoreEntity;
    }

    public void setRoundScoreEntity(RoundScoreEntity roundScoreEntity) {
        this.roundScoreEntity = roundScoreEntity;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }
}
