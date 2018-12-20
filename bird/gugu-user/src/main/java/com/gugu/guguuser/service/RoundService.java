package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.RoundDao;
import com.gugu.gugumodel.entity.RoundEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class RoundService {
    @Autowired
    RoundDao roundDao;

    /**
     * 根据roundId获取详细信息
     * @param roundId
     * @return
     */
    public RoundEntity getMessageById(Long roundId){
        return roundDao.getMessageById(roundId);
    }
}
