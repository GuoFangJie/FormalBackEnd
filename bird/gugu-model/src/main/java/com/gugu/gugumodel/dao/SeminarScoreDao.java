package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.SeminarScoreMapper;
import com.gugu.gugumodel.pojo.entity.SeminarScoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class SeminarScoreDao{
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    public ArrayList<SeminarScoreEntity> getTeamAllScore(Long team_id) {
        return seminarScoreMapper.getTeamAllScore(team_id);
    }
}
