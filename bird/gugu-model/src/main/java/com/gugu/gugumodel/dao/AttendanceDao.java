package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.entity.strategy.CourseMemberLimitStrategyEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.*;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class AttendanceDao {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    @Autowired
    RoundMapper roundMapper;
    @Autowired
    RoundScoreMapper roundScoreMapper;
    @Autowired
    CourseMapper courseMapper;

    /**
     * 修改展示的顺序
     * @param teamOrder
     * @param attendanceId
     * @throws NotFoundException
     */
    public void editAttendanceOrder(Byte teamOrder,Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("记录不存在");
        }else{
            attendanceMapper.editTeamOrder(teamOrder,attendanceId);
        }
    }

    /**
     * 删除展示
     * @param attendanceId
     * @throws NotFoundException
     */
    public void deleteAttendance(Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("记录不存在");
        }else{
            attendanceMapper.deleteById(attendanceId);
        }
    }

    /**
     * 修改报告文件的路径
     * @param path
     * @param fileName
     * @param attendanceId
     * @throws NotFoundException
     */
    public void updloadReport(String path,String fileName,Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("数据不存在");
        }else{
            attendanceMapper.uploadReport(path,fileName,attendanceId);
        }
    }

    /**
     * 上传PPT
     * @param path
     * @param fileName
     * @param attendanceId
     * @throws NotFoundException
     */
    public void updloadPPT(String path,String fileName,Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("数据不存在");
        }else{
            attendanceMapper.uploadPPT(path,fileName,attendanceId);
        }
    }

    /**
     * 获取报告文件信息
     * @param attendanceId
     * @return
     */
    public FileEntity getReportPath(Long attendanceId){
        return attendanceMapper.getReportPath(attendanceId);
    }

    /**
     * 获取ppt文件信息
     * @param attendanceId
     * @return
     */
    public FileEntity getPptPath(Long attendanceId){
        return attendanceMapper.getPptPath(attendanceId);
    }

    /**
     * 根据id获取展示的详细信息
     */
    public AttendanceEntity getById(Long attendanceId){
        return attendanceMapper.getById(attendanceId);
    }
    /**
     * 获取某次讨论课报名小组列表
     */
    public ArrayList<AttendanceEntity> getBySeminarKlassId(Long seminarKlassId){
        return attendanceMapper.getBySeminarKlassId(seminarKlassId);
    }

    /**ljy
     * 报名展示
     * @param attendanceEntity
     * @return
     */
    public Long newAttendance(AttendanceEntity attendanceEntity) throws SQLException {
        if(attendanceMapper.getAttendanceByTeamOrder(attendanceEntity.getTeamOrder(),attendanceEntity.getKlassSeminarId())!=null){
            throw new SQLException("已存在");
        }
        attendanceMapper.newAttendance(attendanceEntity);
        return attendanceEntity.getId();
    }

    /**
     * 计算seminar总分
     * @param courseEntity
     * @param seminarScoreEntity
     * @return
     */
    public Float calculateSeminarScore(CourseEntity courseEntity, SeminarScoreEntity seminarScoreEntity){
        float allScore=0F;
        if(seminarScoreEntity.getPresentationScore()!=null){
            allScore+=seminarScoreEntity.getPresentationScore()*courseEntity.getPresentationPercentage()/100;
        }
        if(seminarScoreEntity.getQuestionScore()!=null){
            allScore+=seminarScoreEntity.getQuestionScore()*courseEntity.getQuestionPercentage()/100;
        }
        if(seminarScoreEntity.getReportScore()!=null){
            allScore+=seminarScoreEntity.getReportScore()*courseEntity.getReportPercentage()/100;
        }
        return allScore;
    }

    /**
     * 计算round总分
     * @param courseEntity
     * @param roundScoreEntity
     * @return
     */
    public Float calculateRoundScore (CourseEntity courseEntity,RoundScoreEntity roundScoreEntity){
        float allScore=0F;
        if(roundScoreEntity.getPresentationScore()!=null){
            allScore+=roundScoreEntity.getPresentationScore()*courseEntity.getPresentationPercentage()/100;
        }
        if(roundScoreEntity.getQuestionScore()!=null){
            allScore+=roundScoreEntity.getQuestionScore()*courseEntity.getQuestionPercentage()/100;
        }
        if(roundScoreEntity.getReportScore()!=null){
            allScore+=roundScoreEntity.getReportScore()*courseEntity.getReportPercentage()/100;
        }
        return allScore;
    }

    /**
     * 计算轮次分数
     * 0 平均分
     * 1 最高分
     * @param
     */
    public void calculateFinalRoundScore(Long teamId,Long roundId,Long courseId){
        ArrayList<SeminarScoreEntity> seminarScoreEntities=seminarScoreMapper.getRoundSeminarScore(teamId,roundId);
        RoundEntity roundEntity=roundMapper.getRoundMessageById(roundId);
        ArrayList<Float> questionScore=new ArrayList<>();
        ArrayList<Float> presentationScore=new ArrayList<>();
        ArrayList<Float> reportScore=new ArrayList<>();
        Float report=0F;
        Float question=0F;
        Float presentation=0F;
        //收集讨论课成绩
        for(SeminarScoreEntity seminarScoreEntity:seminarScoreEntities){
            if(seminarScoreEntity.getReportScore()!=null){
                reportScore.add(seminarScoreEntity.getReportScore());
            }
            if(seminarScoreEntity.getQuestionScore()!=null){
                questionScore.add(seminarScoreEntity.getQuestionScore());
            }
            if(seminarScoreEntity.getPresentationScore()!=null){
                presentationScore.add(seminarScoreEntity.getPresentationScore());
            }
        }
        //计算展示成绩
        if(roundEntity.getPresentationScoreMethod().equals(0)){
            presentation=calculateAverage(presentationScore,seminarScoreEntities.size());
        }else{
            presentation=calculateHighest(presentationScore);
        }
        //计算提问成绩
        if(roundEntity.getQuestionScoreMethod().equals(0)){
            question=calculateAverage(questionScore,seminarScoreEntities.size());
        }else{
            question=calculateHighest(questionScore);
        }
        //计算报告成绩
        if(roundEntity.getReportScoreMethod().equals(0)){
            report=calculateAverage(reportScore,seminarScoreEntities.size());
        }else{
            report=calculateHighest(reportScore);
        }
        RoundScoreEntity roundScoreEntity=roundScoreMapper.getTeamRoundScore(roundId,teamId);
        //判断之前有没有round_score的记录
        if(roundScoreEntity==null){
            roundScoreEntity=new RoundScoreEntity();
            roundScoreEntity.setPresentationScore(presentation);
            roundScoreEntity.setQuestionScore(question);
            roundScoreEntity.setReportScore(report);
            roundScoreEntity.setRoundId(roundId);
            roundScoreEntity.setTeamId(teamId);
            roundScoreEntity.setTotalScore(calculateRoundScore(courseMapper.getCourseById(courseId),roundScoreEntity));
            roundScoreMapper.newRoundScore(roundScoreEntity);
        }else{
            roundScoreEntity.setReportScore(report);
            roundScoreEntity.setQuestionScore(question);
            roundScoreEntity.setPresentationScore(presentation);
            roundScoreEntity.setTotalScore(calculateRoundScore(courseMapper.getCourseById(courseId),roundScoreEntity));
            roundScoreMapper.editRoundScore(roundScoreEntity);
        }
    }

    /**
     *
     * @param seminarScoreEntities
     * @param num
     * 计算平均分
     * @return
     */
    public Float calculateAverage(ArrayList<Float> seminarScoreEntities,Integer num){
        Float all=0F;
        for(Float seminarScoreEntity:seminarScoreEntities){
            all+=seminarScoreEntity;
        }
        return all/num;
    }

    /**
     * 计算最高分
     * @param scores
     * @return
     */
    public Float calculateHighest(ArrayList<Float> scores){
        Float highest=-1F;
        for(Float s:scores){
            if(highest<s){
                highest=s;
            }
        }
        return highest;
    }

    /**
     * 1. 给报告打分
     * 2. 给展示打分
     * 3. 给提问打分
     */
    public void setSeminarScore(Long roundId,Long klassSeminarId,Long teamId,Float score,int type,Long courseId){
        SeminarScoreEntity seminarScoreEntity=seminarScoreMapper.getSeminarScore(klassSeminarId, teamId);
        if(type==1){
            if(seminarScoreEntity==null){
                seminarScoreEntity=new SeminarScoreEntity();
                seminarScoreEntity.setTeamId(teamId);
                seminarScoreEntity.setKlassSeminarId(klassSeminarId);
                seminarScoreEntity.setReportScore(score);
                seminarScoreEntity.setTotalScore(calculateSeminarScore(courseMapper.getCourseById(courseId),seminarScoreEntity));
                seminarScoreMapper.newSeminarScore(seminarScoreEntity);
            }else{
                seminarScoreEntity.setReportScore(score);
                seminarScoreEntity.setTotalScore(calculateSeminarScore(courseMapper.getCourseById(courseId),seminarScoreEntity));
                seminarScoreMapper.setSeminarScore(seminarScoreEntity);
            }
        }else if(type==2){
            if(seminarScoreEntity==null){
                seminarScoreEntity=new SeminarScoreEntity();
                seminarScoreEntity.setTeamId(teamId);
                seminarScoreEntity.setKlassSeminarId(klassSeminarId);
                seminarScoreEntity.setPresentationScore(score);
                seminarScoreEntity.setTotalScore(calculateSeminarScore(courseMapper.getCourseById(courseId),seminarScoreEntity));
                seminarScoreMapper.newSeminarScore(seminarScoreEntity);
            }else{
                seminarScoreEntity.setPresentationScore(score);
                seminarScoreEntity.setTotalScore(calculateSeminarScore(courseMapper.getCourseById(courseId),seminarScoreEntity));
                seminarScoreMapper.setSeminarScore(seminarScoreEntity);
            }
        }else{
            if(seminarScoreEntity==null){
                seminarScoreEntity=new SeminarScoreEntity();
                seminarScoreEntity.setTeamId(teamId);
                seminarScoreEntity.setKlassSeminarId(klassSeminarId);
                seminarScoreEntity.setQuestionScore(score);
                seminarScoreEntity.setTotalScore(calculateSeminarScore(courseMapper.getCourseById(courseId),seminarScoreEntity));
                seminarScoreMapper.newSeminarScore(seminarScoreEntity);
            }else{
                Float end=score;
                if(seminarScoreEntity.getQuestionScore()!=null&&seminarScoreEntity.getQuestionScore()>score){
                    score=seminarScoreEntity.getQuestionScore();
                }
                seminarScoreEntity.setQuestionScore(score);
                seminarScoreEntity.setTotalScore(calculateSeminarScore(courseMapper.getCourseById(courseId),seminarScoreEntity));
                seminarScoreMapper.setSeminarScore(seminarScoreEntity);
            }
        }
        calculateFinalRoundScore(teamId,roundId,courseId);
    }
}
