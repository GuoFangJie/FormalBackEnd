package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.SeminarScoreEntity;

import java.util.ArrayList;

public interface SeminarScoreDao {
    ArrayList<SeminarScoreEntity> getTeamAllScore(Long team_id);
}
