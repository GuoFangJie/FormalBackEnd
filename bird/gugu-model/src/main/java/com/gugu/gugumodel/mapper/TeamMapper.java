package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TeamMapper {
    TeamEntity findTeamById(Long team_id);

    Long getLeaderId(Long teamId);

    void updateTeam(TeamEntity teamEntity);

    void deleteStudentFromTeam(Long teamId);

    void buildRelationStuAndTeam(@Param("studentId") Long studentId, @Param("teamEntity") TeamEntity teamEntity);

    /**
     * 根据id删除team
     * @param teamId
     */
    void deleteTeam(Long teamId);

    void deleteStudentTeamRelation(Long teamId);

    void removeMember(@Param("teamId") Long teamId,@Param("studentId") Long studentId);



    /**
     * 获取某一班级下所有的小组
     * @param klassId
     * @return
     */
    ArrayList<Long> getTeamIdByKlassId(Long klassId);
    /**
     * 删除班级下的小组
     */
    void deleteByKlassId(Long klassId);

    /**@author ljy
     * 新建队伍
     * @param
     * @return
     */
    Long newTeam(TeamEntity teamEntity);


    /**@author ljy
     * 根据teamId获取klassId
     * @param
     * @return
     */
    Long getKlassIdByTeamId(Long teamId);
}
