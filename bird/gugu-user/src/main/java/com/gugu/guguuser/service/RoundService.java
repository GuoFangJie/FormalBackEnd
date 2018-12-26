package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.RoundEntity;
import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.entity.SeminarScoreEntity;
import com.gugu.gugumodel.entity.TeamScoreInRoundEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.RoundScoreMapper;
import com.gugu.guguuser.util.SerialUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class RoundService {
    @Autowired
    RoundDao roundDao;
    @Autowired
    SeminarScoreDao seminarScoreDao;
    @Autowired
    KlassDao klassDao;
    @Autowired
    KlassRoundDao klassRoundDao;
    @Autowired
    RoundScoreMapper roundScoreMapper;
    @Autowired
    TeamDao teamDao;
    @Autowired
    SeminarDao seminarDao;
    @Autowired
    SerialUtil serialUtil;

    /**
     * 根据roundId获取详细信息
     * @param roundId
     * @return
     */
    public RoundEntity getMessageById(Long roundId){
        return roundDao.getMessageById(roundId);
    }

    /**
     * 根据roundid修改round信息
     */
    public void editRoundMessage(RoundEntity roundEntity) throws NotFoundException {
        roundDao.editRoundMessage(roundEntity);
    }
    /**
     * 新建round记录
     */
    public Long newRound(RoundEntity roundEntity){
        //计算round_serial
        ArrayList<Byte> roundSerial=roundDao.getSerial(roundEntity.getCourseId());
        serialUtil.setSerialList(roundSerial);
        roundEntity.setRoundSerial(serialUtil.calcuSerial());
        roundDao.newRound(roundEntity);
        Long newRoundId=roundEntity.getId();
        //获取课程下所有班级
        ArrayList<Long> klassList=klassDao.getKlassIdByCourseId(roundEntity.getCourseId());
        //创建班级下round的副本
        for(int i=0;i<klassList.size();i++){
            klassRoundDao.newKlassRound(klassList.get(i),newRoundId);
        }
        return newRoundId;
    }
    /**
     * 获取一个轮次下所有小组的成绩
     */
    public ArrayList<RoundScoreEntity> getAllTeamScore(Long roundId){
        return roundDao.getAllTeamScore(roundId);
    }

    /**
     * 根据roundid和teamid获取成绩
     */
    public RoundScoreEntity getScoreByRoundAndTeam(Long roundId,Long teamId){
        return roundDao.getByRoundAndTeam(roundId,teamId);
    }

    /**
     * 修改round数据
     * @param roundScoreEntity
     * @throws NotFoundException
     */
    public void editRoundScore(RoundScoreEntity roundScoreEntity) throws NotFoundException {
        roundScoreEntity.setTotalScore((roundScoreEntity.getPresentationScore()+roundScoreEntity.getQuestionScore()+roundScoreEntity.getReportScore())/3);
        roundDao.editRoundScore(roundScoreEntity);
    }

    /**
     * 获取一个课程下所有的round
     */
    public ArrayList<RoundEntity> getRoundMessageByCourseId(Long courseId){
        return roundDao.getRoundMessageByCourseId(courseId);
    }

    /**@author ljy
     * 根据roundId获取roundSerial
     */
    public Byte getRoundSerialById(Long roundId){
        return roundDao.getRoundSerialById(roundId);
    }

    /**
     * 获取某轮下小组所有成绩
     * @param teamId
     * @param roundId
     * @return
     */
    public TeamScoreInRoundEntity getTeamAllScoreInRoundT(Long teamId, Long roundId, Long courseId){
        //获取所有的讨论课成绩
        ArrayList<SeminarScoreEntity> seminarScoreEntities=seminarScoreDao.getAllSeminarScore(courseId,roundId,teamId);
        for(int i=0;i<seminarScoreEntities.size();i++){
            seminarScoreEntities.get(i).setSeminarEntity(seminarDao.getSeminarByKlassSeminarId(seminarScoreEntities.get(i).getKlassSeminarId(),courseId));
        }
        TeamScoreInRoundEntity teamScoreInRoundEntity=new TeamScoreInRoundEntity();
        teamScoreInRoundEntity.setSeminarScoreEntities(seminarScoreEntities);
        teamScoreInRoundEntity.setTeamEntity(teamDao.getTeamById(teamId));
        //获取轮次下总成绩
        teamScoreInRoundEntity.setRoundScoreEntity(roundDao.getTeamTotalScore(teamId,roundId,courseId));
        return teamScoreInRoundEntity;
    }

    /**
     * 获取某轮下小组所有成绩
     * @param teamId
     * @param roundId
     * @return
     */
    public ArrayList<SeminarScoreEntity> getTeamAllScoreInRound(Long teamId, Long roundId){
        return seminarScoreDao.getTeamAllScoreInRound(teamId,roundId);
    }
}
