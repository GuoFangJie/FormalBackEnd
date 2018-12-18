package com.gugu.gugumodel.dao;


import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;

public interface TeamDao {
    TeamEntity getTeamById(Long team_id);

    void updateTeam(TeamEntity teamEntity);

    void deleteStudentFromTeam(Long teamId);

    void buildRelationStuAndTeam(Long studentId,TeamEntity teamEntity);
    void deleteTeam(Long teamId);

    void deleteStudentTeamRelation(Long teamId);

    void addMember(Long teamId,Long studentId);

    void removeMember(Long teamId,Long studentId);
}
