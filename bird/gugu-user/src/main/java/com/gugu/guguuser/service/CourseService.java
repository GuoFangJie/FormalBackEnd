package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.RoundMapper;
import com.gugu.guguuser.controller.vo.RoundTeamsScoreMessageVO;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    @Autowired
    SeminarScoreDao seminarScoreDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    KlassDao klassDao;
    @Autowired
    ShareMessageDao shareMessageDao;

    @Autowired
    SeminarDao seminarDao;
    @Autowired
    RoundMapper roundMapper;

    /**
     * 获取与学生相关的基本课程信息
     * @param
     * @return
     */
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long userId,String role) {
        return courseDao.findSimpleCourseEntityByUserId(userId,role);
    }

    /**
     * 获取所有的课程
     * @return
     */
    public ArrayList<CourseEntity> getAllCourse(){
        return courseDao.getAllCourse();
    }

    /**
     * 新建课程
     * @param courseEntity
     * @return
     */
    public Long newCourse(CourseEntity courseEntity){
        //新建课程 获取ID
        courseDao.newCourse(courseEntity);
        //加入组队限制
        courseDao.addTeamStrategy(courseEntity);
        //加入冲突课程限制
        courseDao.addConflictStrategy(courseEntity);
        return courseEntity.getId();
    }

    /**
     * 根据courseId获取课程详细信息
     * @param id
     * @return
     */
    public CourseEntity getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    /**
     * 根据courseId删除课程
     * @param id
     * @throws Exception
     */
    public void deleteCourseById(Long id) throws NotFoundException {
        courseDao.deleteCourseById(id);
        ArrayList<KlassEntity> klassEntities=klassDao.getKlassByCourseId(id);
        for(int i=0;i<klassEntities.size();i++){
            klassDao.deleteKlassById(klassEntities.get(i).getId());
        }
    }

    /**
     * 获取小组的所有成绩记录
     * @param student_id
     * @param course_id
     * @return
     */
    public ArrayList<SeminarScoreEntity> getTeamAllScore(Long student_id, Long course_id) {
        Long team_id=klassStudentDao.getTeamId(student_id,course_id);
        return seminarScoreDao.getTeamAllScore(team_id);
    }

    /**
     * 通过teamid获取小组信息
     * @param teamId
     * @return
     */
    public TeamEntity getTeamById(Long teamId) {
        return teamDao.getTeamById(teamId);
    }

    /**
     * 获取课程下的班级信息
     * @param courseId
     * @return
     */
    public ArrayList<KlassEntity> getKlassByCourseId(Long courseId) {
        return klassDao.getKlassByCourseId(courseId);
    }

    /**
     * 获取课程相关的所有讨论课共享关系
     * @param courseId
     * @return
     */
    public ArrayList<ShareMessageEntity> getAllShareSeminar(Long courseId) {
        return courseDao.getSeminarShareMessage(courseId);
    }
    public ArrayList<ShareMessageEntity> getAllShareTeam(Long courseId){
        return courseDao.getTeamShareMessage(courseId);
    }

    /**
     * 删除分享关系，并删除从课程的部分数据
     * @param shareId
     * @param type
     * @return
     */
    public boolean deleteCourseShare(Long shareId,Integer type){
        if(type.equals(1)){
            return courseDao.deleteSeminarShare(shareId);
        }else{
            return courseDao.deleteTeamShare(shareId);
        }
    }

    /**
     * 获取课程下所有的小组信息
     * @param courseId
     * @return
     */
    public ArrayList<TeamEntity> getAllTeamByCourse(Long courseId){
        return  courseDao.getAllTeamByCourse(courseId);
    }

    /**
     * 新建申请
     * @param mainCourseId
     * @param subCourseId
     * @param type
     */
    public void newApplication(Long mainCourseId,Long subCourseId,Integer type){
        shareMessageDao.newShareSeminarApplication(mainCourseId,subCourseId,type);
    }

    /**
     * 获取特定课程下每个轮次每个小组的总分以及在该轮下每个讨论课下的成绩
     * @param roundId
     * @param courseId
     * @return
     */
    public RoundTeamsScoreMessageVO getTeamScoreInRound(Long courseId,Long roundId){
        //存放每轮次下所有小组成绩list
        ArrayList<TeamScoreInRoundEntity> teamScoreInRoundEntities=new ArrayList<TeamScoreInRoundEntity>();
        //获取本轮次下所有小组在本轮此下的总成绩
        ArrayList<RoundScoreEntity> roundScoreEntities=courseDao.getTeamTotalScoreInRound(courseId,roundId);
        for(int i=0;i<roundScoreEntities.size();i++){
            Long teamId=roundScoreEntities.get(i).getTeamId();
            ArrayList<SeminarScoreEntity> seminarScoreEntities=seminarScoreDao.getAllSeminarScore(courseId,roundId,teamId);
            for(int j=0;j<seminarScoreEntities.size();j++){
                seminarScoreEntities.get(j).setSeminarEntity(seminarDao.getSeminarByKlassSeminarId(seminarScoreEntities.get(j).getKlassSeminarId(),courseId));
            }
            TeamScoreInRoundEntity teamScoreInRoundEntity=new TeamScoreInRoundEntity();
            teamScoreInRoundEntity.setTeamEntity(teamDao.getTeamById(teamId));
            teamScoreInRoundEntity.setRoundScoreEntity(roundScoreEntities.get(i));
            teamScoreInRoundEntity.setSeminarScoreEntities(seminarScoreEntities);
            teamScoreInRoundEntities.add(teamScoreInRoundEntity);
        }
        //找到该课程下该轮次下所有的讨论课
        //ArrayList<SeminarEntity> seminarEntities= seminarDao.getSeminarByCourseAndRound(courseId,roundId);
        RoundTeamsScoreMessageVO roundTeamsScoreMessageVO=new RoundTeamsScoreMessageVO();
        roundTeamsScoreMessageVO.setRoundId(roundId);
        roundTeamsScoreMessageVO.setRoundSerial(roundMapper.getRoundSerialById(roundId));
        roundTeamsScoreMessageVO.setTeamScoreInRoundEntities(teamScoreInRoundEntities);
        return roundTeamsScoreMessageVO;
    }
}
