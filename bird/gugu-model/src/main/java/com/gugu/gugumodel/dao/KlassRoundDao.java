package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassRoundMapper;
import com.gugu.gugumodel.mapper.RoundScoreMapper;
import com.gugu.gugumodel.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class KlassRoundDao {
    @Autowired
    KlassRoundMapper klassRoundMapper;
    @Autowired
    RoundScoreMapper roundScoreMapper;
    @Autowired
    TeamMapper teamMapper;
    /**
     * 根据klassId删除记录，同时删除round_score中的记录
     * @param klassId
     * @return
     */
    public boolean deleteKlassRoundByKlassId(Long klassId){
        klassRoundMapper.deleteByKlass(klassId);
        ArrayList<Long> teamId=teamMapper.getTeamIdByKlassId(klassId);
        for(int i=0;i<teamId.size();i++){
            roundScoreMapper.deleteByTeamId(teamId.get(i));
        }
        return true;
    }

    /**
     * 新建klass_round记录
     * @param klassId
     * @param roundId
     * @return
     */
    public boolean newKlassRound(Long klassId,Long roundId){
        klassRoundMapper.newKlassRound(klassId,roundId);
        return true;
    }
}
