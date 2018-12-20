package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.entity.TeamEntity;

/**
 * @author ren
 */
public class RoundScoreMessageVO {
    RoundScoreEntity roundScoreEntity;
    TeamEntity teamEntity;

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
