package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassSeminarMapper;
import com.gugu.gugumodel.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class KlassSeminarDao {
    @Autowired
    KlassSeminarMapper klassSeminarMapper;
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    public boolean deleteKlassSeminar(Long klassId){
        ArrayList<Long> klassSeminarId=klassSeminarMapper.getKlassSeminarIdByKlass(klassId);
        klassSeminarMapper.deleteByKlass(klassId);
        for(int i=0;i<klassSeminarId.size();i++){
            seminarScoreMapper.deleteByKlassSeminarId(klassSeminarId.get(i));
        }
        return true;
    }
}
