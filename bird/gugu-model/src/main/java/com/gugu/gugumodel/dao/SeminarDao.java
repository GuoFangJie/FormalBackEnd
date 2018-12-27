package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.*;
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
    RoundMapper roundMapper;

    @Autowired
    KlassSeminarMapper klassSeminarMapper;

    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    CourseMapper courseMapper;
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
    public ArrayList<KlassEntity> getKlassSeminatIn(Long seminarId){
        ArrayList<Long> klassId=seminarMapper.getKlassIdBySeminerId(seminarId);
        System.out.println(klassId);
        ArrayList<KlassEntity> klassEntities=new ArrayList<>();
        for(int i=0;i<klassId.size();i++){
            klassEntities.add(klassMapper.getKlassById(klassId.get(i)));
        }
       return klassEntities;
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
        ArrayList<Long> klassSeminarId=klassSeminarMapper.getAllKlassSeminarId(seminarId);
        if(klassSeminarId==null)
        {
            return false;
        }
        for(int i=0;i<klassSeminarId.size();i++){
            //按照id删除klass_seminar表中的对应记录
            klassSeminarMapper.deleteKlassSeminarById(klassSeminarId.get(i));
            //按照klassSeminarId删除seminar_score表中的记录
            seminarScoreMapper.deleteByKlassSeminarId(klassSeminarId.get(i));
        }

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
        Long klassSeminarId=klassSeminarMapper.getKlassSeminarId(seminarId,classId);
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
        KlassSeminarEntity klassSeminarEntity=new KlassSeminarEntity();
        klassSeminarEntity.setSeminarEntity(seminarEntity);
        KlassSeminarEntity klassSeminarEntity1=new KlassSeminarEntity();
        klassSeminarEntity1= klassSeminarMapper.getSeminarInClass(seminarId,klassId);
        klassSeminarEntity.setReportDDL(klassSeminarEntity1.getReportDDL());
        klassSeminarEntity.setStatus(klassSeminarEntity1.getStatus());
        klassSeminarEntity.setKlassSeminarId(klassSeminarEntity1.getKlassSeminarId());
        return klassSeminarEntity;
    }


    /**@author ljy
     * 设置讨论课轮次
     * @param seminarId
     * @return
     */
    public boolean setSeminarRound(Long seminarId,RoundEntity roundEntity){
        Long roundId=seminarMapper.getRoundId(seminarId);
        roundMapper.setSeminarRound(roundId,roundEntity);
        return true;
    }


    /**@author ljy
     * 设置讨论课状态
     * @param seminarId
     * @return
     */
    public boolean setSeminarStatus(Long seminarId,Long classId,Byte status){
        return klassSeminarMapper.setSeminarStatus(seminarId,classId,status);
    }

    /**@author ljy
     * 设置班级下讨论课书面报告截止时间
     * @param seminarId
     * @return
     */
    public boolean setSeminarReportddl(Long seminarId,Long classId,Date date){
        return klassSeminarMapper.setSeminarReportddl(seminarId,classId,date);
    }

    /**@author ljy
     * 获取班级下小组在一次讨论课下的成绩
     * @param seminarId
     * @return
     */
    public SeminarScoreEntity getSeminarScore(Long seminarId, Long teamId){
        Long classId=teamMapper.getKlassIdByTeamId(teamId);
        Long klassSeminarId=klassSeminarMapper.getKlassSeminarId(seminarId,classId);
        return seminarScoreMapper.getSeminarScore(klassSeminarId,teamId);
    }

    /**@author ljy
     * 按照seminarid修改队伍讨论课成绩
     * @param seminarId
     * @return
     */
    public boolean setSeminarScore(Long seminarId,Long teamId,SeminarScoreEntity seminarScoreEntity){
        Long classId=teamMapper.getKlassIdByTeamId(teamId);
        Long klassSeminarId=klassSeminarMapper.getKlassSeminarId(seminarId,classId);
        seminarScoreEntity.setKlassSeminarId(klassSeminarId);
        //CourseEntity courseEntity=courseMapper.getCourseByTeamId(teamId);
        return seminarScoreMapper.setSeminarScore(seminarScoreEntity);
    }

    /**@author ljy
     * 按照seminarid获取讨论课所有小组成绩
     * @param seminarId
     * @return
     */
    public ArrayList<SeminarScoreEntity> getSeminarAllScore(Long seminarId,Long classId){
        Long klassSeminarId=klassSeminarMapper.getKlassSeminarId(seminarId,classId);
        ArrayList<SeminarScoreEntity> seminarScoreEntities=seminarScoreMapper.getSeminarAllScore(klassSeminarId);
        for(int i=0;i<seminarScoreEntities.size();i++){
            seminarScoreEntities.get(i).setTeamEntity(teamMapper.getTeamById(seminarScoreEntities.get(i).getTeamId()));
        }
        return seminarScoreEntities;
    }

    /**@author TYJ
     * 获取一个课程下所有的讨论课
     * @param courseId
     * @return
     */
    public ArrayList<SeminarEntity> getSeminarByCourseId(Long courseId){
        return seminarMapper.getSeminarByCourseId(courseId);
    }

    /**@author ljy
     * 获取一个课程下已有的serial
     * @param courseId
     * @return
     */
    public ArrayList<Byte> getSerial(Long courseId){
        return seminarMapper.getSerial(courseId);
    }


    /**@author ljy
     * 根据courseId和roundId获取讨论课信息
     * @param courseId
     * @return
     */
    public ArrayList<SeminarEntity> getSeminarByCourseAndRound(Long courseId,Long roundId){
           return seminarMapper.getSeminarByCourseAndRound(courseId,roundId);
    }

    /**@author ljy
     * 根据klassSeminarId获取seminar
     * @param klassSeminarId
     * @return
     */
    public SeminarEntity getSeminarByKlassSeminarId(Long klassSeminarId,Long courseId){
        return seminarMapper.getSeminarByKlassSeminarId(klassSeminarId,courseId);
    }

    /**
     * 获取某次展示的成绩
     */
    public SeminarScoreEntity getOnceSeminarScore(Long klassSeminarId,Long teamId){
        return seminarScoreMapper.getSeminarScore(klassSeminarId,teamId);
    }
}
