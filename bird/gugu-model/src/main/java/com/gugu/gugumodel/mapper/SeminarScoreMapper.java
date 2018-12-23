package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.entity.SeminarScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Mapper
@Repository
public interface SeminarScoreMapper {
    /**
     * 获取与小组相关的所有成绩
     * @param team_id
     * @return
     */
    ArrayList<SeminarScoreEntity> getTeamAllScore(Long team_id);

    /**
     * 根据klassSeminarId删除讨论课成绩记录
     */
    void deleteByKlassSeminarId(Long klassSeminarId);


    /**@author ljy
     * 获取班级下小组在一次讨论课下的成绩
     * @param klassSeminarId
     * @return
     */
    public SeminarScoreEntity getSeminarScore(Long klassSeminarId,Long teamId);

    /**@author ljy
     * 按照seminarid修改队伍讨论课成绩
     * @return
     */
    public boolean setSeminarScore(SeminarScoreEntity seminarScoreEntity);

    /**@author ljy
     * 按照seminarid获取讨论课所有小组成绩
     * @param klassSeminarId
     * @return
     */
    public ArrayList<SeminarScoreEntity>getSeminarAllScore(Long klassSeminarId);

    /**
     * 根据团队id和roundid获取成绩
     * @param teamId
     * @param roundId
     * @return
     */
    ArrayList<SeminarScoreEntity> getRoundSeminarScore(Long teamId,Long roundId);
}
