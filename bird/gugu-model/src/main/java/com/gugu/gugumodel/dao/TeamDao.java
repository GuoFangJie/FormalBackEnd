package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.mapper.*;
import com.gugu.gugumodel.entity.TeamEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TeamDao{
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    TeamValidRequestMapper teamValidRequestMapper;
    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    public TeamEntity getTeamById(Long team_id) {
        return teamMapper.findTeamById(team_id);
    }


    public void updateTeam(TeamEntity teamEntity){
        teamMapper.updateTeam(teamEntity);
    }


    public void deleteStudentFromTeam(Long teamId){
        teamMapper.deleteStudentFromTeam(teamId);
    }


    public void buildRelationStuAndTeam(Long studentId,TeamEntity teamEntity){
        teamMapper.buildRelationStuAndTeam(studentId,teamEntity.getId());
    }


    public void deleteTeam(Long teamId){
        teamMapper.deleteTeam(teamId);
    }


    public void deleteStudentTeamRelation(Long teamId){
        teamMapper.deleteStudentTeamRelation(teamId);
    }

    /*数据库修改后修改的
    * */
    public void addMember(Long teamId,Long studentId){
       // TeamEntity teamEntity= teamMapper.findTeamById(teamId);
        klassStudentMapper.addMember(teamId,studentId);
    }


    public void removeMember(Long teamId,Long studentId){
        teamMapper.removeMember(teamId,studentId);
    }


    public Long getTeacherIdByCourse(Long courseId){
       return courseMapper.getTeacherIdByCourse(courseId);
    }


    public void teamValidRequest(TeamValidEntity teamValidEntity){
        teamValidRequestMapper.teamValidRequest(teamValidEntity);
    }


    public Long getTeamValidStatus(Long teamId){
        return teamValidRequestMapper.getTeamValidStatus(teamId);
    }

    /**
     * 删除班级下的小组
     */
    public boolean deleteByKlassId(Long klassId){
        teamMapper.deleteByKlassId(klassId);
        return true;
    }

    /**@author ljy
     * 新建队伍,返回队伍id
     * @param
     * @return
     */
    public Long newTeam(ArrayList<StudentEntity> memberStudents, TeamEntity teamEntity){
        teamMapper.newTeam(teamEntity);
        for(int i=0;i<memberStudents.size();i++){
            klassStudentMapper.addMember(teamEntity.getId(),memberStudents.get(i).getId());
        }
        Long teamId=teamEntity.getId();
        System.out.println(teamId);
        return teamId;
    }

    /**
     * @author TYJ
     * 修改小组状态
     * @param teamId
     * @param status
     * @return
     */
    public int changeTeamStatus(Long teamId,Byte status){
        return teamMapper.changeTeamStatus(teamId,status);
    }

    /**
     * @author TYJ
     * 删除课程下的所有小组 ,数据库修改后新修改
     * @param courseId
     * @return
     */
    public void deleteAllTeamByCourseId(Long courseId){
        //获取课程下的所有小组
        ArrayList<TeamEntity> teamEntities=teamMapper.getAllTeamByCourseId(courseId);
        for(int i=0;i<teamEntities.size();i++){
            //删除klass_team表中的联系
            klassStudentMapper.removeKlassTeamRelation(teamEntities.get(i).getId());
            //删除team_student表中的联系
            klassStudentMapper.removeStudentTeamRelation(teamEntities.get(i).getId());
        }
        teamMapper.deleteAllTeamByCourseId(courseId);
    }

    /**
     * @author TYJ
     * 根据课程获得所有小组
     * @param courseId
     * @return
     */
    public ArrayList<TeamEntity> getAllTeamByCourseId(Long courseId){
        return teamMapper.getAllTeamByCourseId(courseId);
    }

    /**
     * @author TYJ
     * 创建KlassTeam副本
     * @param klassId
     * @param teamId
     */
    public void createKlassTeam(Long klassId,Long teamId){
        teamMapper.createKlassTeam(klassId,teamId);
    }
}
