package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.RoundMapper;
import com.gugu.gugumodel.entity.RoundEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ren
 */
@Repository
public class RoundDao {
    @Autowired
    RoundMapper roundMapper;

    /**
     * 根据id获取round的详细信息
     * @param roundId
     * @return
     */
    public RoundEntity getMessageById(Long roundId){
        return roundMapper.getRoundMessageById(roundId);
    }
}
