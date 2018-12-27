package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.entity.TeamScoreInRoundEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
@Mapper
public interface RoundScoreMapper {
    /**
     * 获取一个轮次下所有小组的得分
     */
    ArrayList<RoundScoreEntity> getAllTeamScore(Long roundId);

    /**
     * 根据teamid删除round_score记录
     * @param teamId
     */
    void deleteByTeamId(Long teamId);

    /**
     * 根据roundid和teamid获取成绩
     * @param roundId
     * @param teamId
     * @return
     */
    RoundScoreEntity getTeamRoundScore(Long roundId,Long teamId);

    /**
     * 修改roundScore记录
     */
    void editRoundScore(RoundScoreEntity roundScoreEntity);

    /**
     * @author ljy
     * 获取小组在本轮此下的总成绩
     * @return
     */
    public ArrayList<RoundScoreEntity> getTeamTotalScoreInRound(Long courseId, Long roundId);

    /**
     * 新建round_score记录
     * @param roundScoreEntity
     */
    public void newRoundScore(RoundScoreEntity roundScoreEntity);
}
