package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.KlassSeminarEntity;
import com.gugu.gugumodel.mapper.KlassMapper;
import com.gugu.gugumodel.mapper.KlassSeminarMapper;
import com.gugu.gugumodel.mapper.SeminarMapper;
import com.gugu.gugumodel.entity.SeminarEntity;
import com.gugu.gugumodel.mapper.SeminarScoreMapper;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ren
 */
@Repository
public class SeminarDao {
    @Autowired
    SeminarMapper seminarMapper;
    @Autowired
    KlassMapper klassMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    /**
     * 获取一个round里面所有的seminar信息
     * @param roundId
     * @return
     */
    public ArrayList<SeminarEntity> getSeminarByRound(Long roundId){
        return seminarMapper.getSeminarsByRound(roundId);
    }

    /**@author ljy
     * 新建讨论课,创建成功后返回seminarId
     * @return Long
     */
    public Long newSeminar(SeminarEntity seminarEntity){
        seminarMapper.newSeminar(seminarEntity);
        return seminarEntity.getId();
    }

    /**@author ljy
     * 获取讨论课所属的班级
     * @param seminarId
     * @return KlassEntiry
     */
    public KlassEntity getKlassSeminatIn(Long seminarId){
        Long klassId=seminarMapper.getKlassIdBySeminerId(seminarId);
        System.out.println(klassId);
       return klassMapper.getKlassById(klassId);
    }

    /**@author ljy
     * 按照id修改讨论课
     * @param seminarEntity
     * @return
     */
    public boolean updateSeminar(SeminarEntity seminarEntity){
        return seminarMapper.updateSeminar(seminarEntity);
    }

    /**@author ljy
     * 按照id删除讨论课
     * @param
     * @return
     */
    public boolean deleteSeminar(Long seminarId){
        //按照id删除讨论课
        seminarMapper.deleteSeminar(seminarId);
        //按照id获取klass_seminar_id
        Long klassSeminarId=klassSeminarMapper.getKlassSeminarId(seminarId);
        if(klassSeminarId==null)
        {
            return false;
        }
        //按照id删除klass_seminar表中的对应记录
        klassSeminarMapper.deleteKlassSeminarById(klassSeminarId);
        //按照klassSeminarId删除seminar_score表中的记录
        seminarScoreMapper.deleteByKlassSeminarId(klassSeminarId);
        return true;
    }


    /**@author ljy
     * 按照id获取讨论课
     * @param
     * @return
     */
    public SeminarEntity getSeminarById(Long seminarId){
        return seminarMapper.getSeminarById(seminarId);
    }

    /**@author ljy
     * 按照id修改班级讨论课（设置不同班级讨论课的报告提交时间）
     * @param seminarId
     * @return
     */
    public boolean setReportDDLInClass(Long seminarId, Long klassId, Date date){
        return klassSeminarMapper.setReportDDLInClass(seminarId,klassId,date);
    }

    /**@author ljy
     * 按照id删除班级讨论课（设置不同班级讨论课的报告提交时间）
     * @param seminarId
     * @return
     */
    public boolean deleteSeminarInClass(Long seminarId,Long classId){
        Long klassSeminarId=klassSeminarMapper.getKlassSeminarId(seminarId);
        klassSeminarMapper.deleteKlassSeminarById(klassSeminarId);
        seminarScoreMapper.deleteByKlassSeminarId(klassSeminarId);
        return true;
    }


    /**@author ljy
     * 按照id获取班级下讨论课
     * @param seminarId
     * @return
     */
    public KlassSeminarEntity getSeminarInClass(Long seminarId, Long klassId){
        SeminarEntity seminarEntity=seminarMapper.getSeminarById(seminarId);
        seminarEntity.set
        return seminarDao.getSeminarInClass(seminarId,klassId);

    }
}
