package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.SeminarScoreMapper;
import com.gugu.gugumodel.entity.SeminarScoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class SeminarScoreDao{
    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    /**
     * 获取小组所有成绩
     * @param team_id
     * @return
     */
    public ArrayList<SeminarScoreEntity> getTeamAllScore(Long team_id) {
        return seminarScoreMapper.getTeamAllScore(team_id);
    }
    /**
     * 获取小组在某轮下的成绩
     */
    public ArrayList<SeminarScoreEntity> getTeamAllScoreInRound(Long teamId,Long roundId){
        return seminarScoreMapper.getRoundSeminarScore(teamId,roundId);
    }

    /**@author lju
     * 获取小组在某课程下某轮次下的所有讨论课成绩
     */
    public ArrayList<SeminarScoreEntity> getAllSeminarScore(Long courseId,Long roundId,Long teamId){
        return seminarScoreMapper.getAllSeminarScore(courseId,roundId,teamId);
    }
}
