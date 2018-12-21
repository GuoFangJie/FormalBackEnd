package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.SeminarDao;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.KlassSeminarEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

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


    /**
     * 新建讨论课,创建成功后返回seminarId
     * @return Long
     */
    public Long newSeminar(SeminarEntity seminarEntity){
        return seminarDao.newSeminar(seminarEntity);
    }

    /**@author ljy
     * 获取讨论课所属的班级
     * @param seminarId
     * @return KlassEntiry
     */
    public KlassEntity getKlassSeminatIn(Long seminarId){
        return seminarDao.getKlassSeminatIn(seminarId);
    }

    /**@author ljy
     * 按照id修改讨论课
     * @param seminarEntity
     * @return
     */
    public boolean updateSeminar(SeminarEntity seminarEntity){
        return seminarDao.updateSeminar(seminarEntity);
    }

    /**@author ljy
     * 按照id删除讨论课
     * @param
     * @return
     */
    public boolean deleteSeminar(Long seminarId){
        return seminarDao.deleteSeminar(seminarId);
    }


    /**@author ljy
     * 按照id获取讨论课
     * @param
     * @return
     */
    public SeminarEntity getSeminarById(Long seminarId){
        return seminarDao.getSeminarById(seminarId);
    }

    /**@author ljy
     * 按照id修改班级讨论课（设置不同班级讨论课的报告提交时间）
     * @param seminarId
     * @return
     */
    public boolean setReportDDLInClass(Long seminarId,Long klassId,Date date){
        return seminarDao.setReportDDLInClass(seminarId,klassId,date);
    }

    /**@author ljy
     * 按照id删除班级讨论课（设置不同班级讨论课的报告提交时间）
     * @param seminarId
     * @return
     */
    public boolean deleteSeminarInClass(Long seminarId,Long classId){
        return seminarDao.deleteSeminarInClass(seminarId,classId);
    }

    /**@author ljy
     * 按照id获取班级下讨论课
     * @param seminarId
     * @return
     */
    public KlassSeminarEntity getSeminarInClass(Long seminarId, Long klassId){
        return seminarDao.getSeminarInClass(seminarId,klassId);
    }
}
