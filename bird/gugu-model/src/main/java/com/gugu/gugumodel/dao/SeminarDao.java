package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.SeminarMapper;
import com.gugu.gugumodel.entity.SeminarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class SeminarDao {
    @Autowired
    SeminarMapper seminarMapper;

    /**
     * 获取一个round里面所有的seminar信息
     * @param roundId
     * @return
     */
    public ArrayList<SeminarEntity> getSeminarByRound(Long roundId){
        return seminarMapper.getSeminarsByRound(roundId);
    }
}
