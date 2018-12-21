package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.RoundMapper;
import com.gugu.gugumodel.entity.RoundEntity;
import com.gugu.gugumodel.mapper.RoundScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class RoundDao {
    @Autowired
    RoundMapper roundMapper;
    @Autowired
    RoundScoreMapper roundScoreMapper;
    /**
     * 根据id获取round的详细信息
     * @param roundId
     * @return
     */
    public RoundEntity getMessageById(Long roundId){
        return roundMapper.getRoundMessageById(roundId);
    }

    /**
     * 编辑round计分方式
     * @param roundEntity
     * @throws NotFoundException
     */
    public void editRoundMessage(RoundEntity roundEntity) throws NotFoundException {
        if(roundMapper.getRoundMessageById(roundEntity.getId())==null){
            throw new NotFoundException("找不到该记录");
        }else{
            roundMapper.editRoundMessage(roundEntity);
        }
    }
    /**
     * 新建round记录
     */
    public Long newRound(RoundEntity roundEntity){
        roundMapper.newRound(roundEntity);
        return roundEntity.getId();
    }
    /**
     * 获取一个轮次下所有的小组成绩
     */
    public ArrayList<RoundScoreEntity> getAllTeamScore(Long roundId){
        return roundScoreMapper.getAllTeamScore(roundId);
    }
    /**
     * 通过roundid和teamid获取成绩
     */
    public RoundScoreEntity getByRoundAndTeam(Long roundId,Long teamId){
        return roundScoreMapper.getTeamRoundScore(roundId,teamId);
    }
    /**
     * 修改roundscore
     */
    public void editRoundScore(RoundScoreEntity roundScoreEntity) throws NotFoundException {
        if(roundScoreMapper.getTeamRoundScore(roundScoreEntity.getRoundId(),roundScoreEntity.getTeamId())==null){
            throw new NotFoundException("该数据不存在");
        }else{
            roundScoreMapper.editRoundScore(roundScoreEntity);
        }
    }

    public ArrayList<RoundEntity> getRoundMessageByCourseId(Long courseId){
        return roundMapper.getRoundMessageByCourseId(courseId);
    }
}
