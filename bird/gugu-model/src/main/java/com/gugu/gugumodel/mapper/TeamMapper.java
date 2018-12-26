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

    void buildRelationStuAndTeam(@Param("studentId") Long studentId, @Param("teamId")Long teamId );

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

    int changeTeamStatus(Long teamId,Byte status);

    /**
     * 获取课程下所有的小组信息
     * @param courseId
     * @return
     */
    ArrayList<TeamEntity> getAllTeamByCourse(Long courseId);

    /**
     * @author TYJ
     * 删除课程下的所有小组
     * @param courseId
     * @return
     */
    void deleteAllTeamByCourseId(Long courseId);


    /**
     * @author TYJ
     * 根据课程获得所有小组
     * @param courseId
     * @return
     */
    ArrayList<TeamEntity> getAllTeamByCourseId(Long courseId);

    /**
     * @author TYJ
     * 创建klass_team副本
     * @param klassId
     * @param teamId
     */
    void createKlassTeam(Long klassId,Long teamId);
    /**
     * @author TYJ
     * 获取当前所有的serial
     * @param klassId
     */
    ArrayList<Byte> getSerial(Long klassId);


    /**
     * @author ljy
     * 根据teamId获取team信息
     * @param teamId
     */
    TeamEntity getTeamById(Long teamId);
}
