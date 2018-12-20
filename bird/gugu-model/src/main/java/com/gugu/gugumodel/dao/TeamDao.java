package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.mapper.SeminarScoreMapper;
import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.mapper.TeamMapper;
import com.gugu.gugumodel.mapper.TeamValidRequestMapper;
import com.gugu.gugumodel.entity.TeamEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDao{
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
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
        teamMapper.buildRelationStuAndTeam(studentId,teamEntity);
    }


    public void deleteTeam(Long teamId){
        teamMapper.deleteTeam(teamId);
    }


    public void deleteStudentTeamRelation(Long teamId){
        teamMapper.deleteStudentTeamRelation(teamId);
    }

    public void addMember(Long teamId,Long studentId){
        TeamEntity teamEntity= teamMapper.findTeamById(teamId);
        teamMapper.addMember(teamEntity,studentId);
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
}
