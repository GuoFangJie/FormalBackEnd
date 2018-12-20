package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.SeminarDao;
import com.gugu.gugumodel.pojo.entity.SeminarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class SeminarService {
    @Autowired
    SeminarDao seminarDao;

    /**
     * 根据roundid获取所有讨论课信息
     * @param roundId
     * @return
     */
    public ArrayList<SeminarEntity> getSeminarByRound(Long roundId){
        return seminarDao.getSeminarByRound(roundId);
    }
}
