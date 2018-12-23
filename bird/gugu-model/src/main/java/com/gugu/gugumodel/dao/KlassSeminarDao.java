package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.KlassSeminarEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
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

    public ArrayList<KlassSeminarEntity> getAllKlassSeminarEntityByKlassId(Long klassId){
        return klassSeminarMapper.getKlassSeminarEntityByKlassId(klassId);
    }

    public boolean addKlassSeminar(Long seminarId, Long klssId){
        return klassSeminarMapper.addKlassSeminar(seminarId,klssId);
    }
}
