package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.SeminarScoreMapper;
import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.mapper.TeamMapper;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl implements TeamDao {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    @Override
    public TeamEntity getTeamById(Long team_id) {
        return teamMapper.findTeamById(team_id);
    }

    @Override
    public void updateTeam(TeamEntity teamEntity){
        teamMapper.updateTeam(teamEntity);
    }

    @Override
    public void deleteStudentFromTeam(Long teamId){
        teamMapper.deleteStudentFromTeam(teamId);
    }

    @Override
    public void buildRelationStuAndTeam(Long studentId,TeamEntity teamEntity){
        teamMapper.buildRelationStuAndTeam(studentId,teamEntity);
    }

    @Override
    public void deleteTeam(Long teamId){
        teamMapper.deleteTeam(teamId);
    }

    @Override
    public void deleteStudentTeamRelation(Long teamId){
        teamMapper.deleteStudentTeamRelation(teamId);
    }

    @Override
    public void addMember(Long teamId,Long studentId){
        TeamEntity teamEntity= teamMapper.findTeamById(teamId);
        teamMapper.addMember(teamEntity,studentId);
    }

    @Override
    public void removeMember(Long teamId,Long studentId){
        teamMapper.removeMember(teamId,studentId);
    }

    /**
     * 删除班级下的小组
     */
    public boolean deleteByKlassId(Long klassId){
        teamMapper.deleteByKlassId(klassId);
        return true;
    }
}
