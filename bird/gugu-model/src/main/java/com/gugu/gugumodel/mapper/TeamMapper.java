package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import com.gugu.gugumodel.pojo.entity.TeamValidEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeamMapper {
    TeamEntity findTeamById(Long team_id);

    Long getLeaderId(Long teamId);

    void updateTeam(TeamEntity teamEntity);

    void deleteStudentFromTeam(Long teamId);

    void buildRelationStuAndTeam(@Param("studentId") Long studentId, @Param("teamEntity") TeamEntity teamEntity);

    void deleteTeam(Long teamId);

    void deleteStudentTeamRelation(Long teamId);

    void addMember(@Param("team") TeamEntity teamEntity,@Param("studentId") Long studentId);

    void removeMember(@Param("teamId") Long teamId,@Param("studentId") Long studentId);


}
