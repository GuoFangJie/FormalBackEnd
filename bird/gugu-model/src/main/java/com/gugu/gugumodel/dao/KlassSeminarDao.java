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

    /**
     * 根据班级id删除班级讨论课
     * @param klassId
     * @return
     */
    public boolean deleteKlassSeminar(Long klassId){
        ArrayList<Long> klassSeminarId=klassSeminarMapper.getKlassSeminarIdByKlass(klassId);
        klassSeminarMapper.deleteByKlass(klassId);
        for(int i=0;i<klassSeminarId.size();i++){
            seminarScoreMapper.deleteByKlassSeminarId(klassSeminarId.get(i));
        }
        return true;
    }

    /**
     * 获取班级下所有的讨论课信息
     * @param klassId
     * @return
     */
    public ArrayList<KlassSeminarEntity> getAllKlassSeminarEntityByKlassId(Long klassId){
        return klassSeminarMapper.getKlassSeminarEntityByKlassId(klassId);
    }

    /**
     * 添加讨论课和班级的关系
     * @param seminarId
     * @param klssId
     * @return
     */
    public boolean addKlassSeminar(Long seminarId, Long klssId){
        return klassSeminarMapper.addKlassSeminar(seminarId,klssId);
    }
}
