package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.SeminarScoreEntity;
import org.apache.ibatis.annotations.Mapper;
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
    public SeminarScoreEntity getSeminarScore(Long klassSeminarId);
}
