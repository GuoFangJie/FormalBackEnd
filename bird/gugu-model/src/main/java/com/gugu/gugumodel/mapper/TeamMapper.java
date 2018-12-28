package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface TeamMapper {
    /**
     * 根据teamid获取小组的信息
     * @param team_id
     * @return
     */
    TeamEntity findTeamById(Long team_id);

    /**
     * 获取队长的id
     * @param teamId
     * @return
     */
    Long getLeaderId(Long teamId);

    /**
     * 更新小组的信息
     * @param teamEntity
     */
    void updateTeam(TeamEntity teamEntity);

    /**
     * 删除小组下所有学生
     * @param teamId
     */
    void deleteStudentFromTeam(Long teamId);

    /**
     * 新建学生和小组的关系
     * @param studentId
     * @param teamId
     */
    void buildRelationStuAndTeam(@Param("studentId") Long studentId, @Param("teamId")Long teamId );

    /**
     * 根据id删除team
     * @param teamId
     */
    void deleteTeam(Long teamId);

    /**
     * 删除小组下所有的学生
     * @param teamId
     */
    void deleteStudentTeamRelation(Long teamId);

    /**
     * 移除小组下某一个学生
     * @param teamId
     * @param studentId
     */
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

    /**
     * 新建队伍和班级的关联
     * @param teamEntity
     */
    void newKlassTeam(TeamEntity teamEntity);


    /**@author ljy
     * 根据teamId获取klassId
     * @param
     * @return
     */
    Long getKlassIdByTeamId(Long teamId);

    /**
     * 修改小组的状态
     * @param teamId
     * @param status
     * @return
     */
    int changeTeamStatus(Long teamId,Byte status);

    /**
     * 获取课程下所有的小组信息
     * @param courseId
     * @return
     */
    ArrayList<TeamEntity> getAllTeamByCourse(Long courseId);

    /**
     * @author TYJ
     * 删除班级下的所有小组
     * @param klassId
     * @return
     */
    void deleteAllTeamByKlassId(Long klassId);


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

    /**
     * @author ljy
     * 根据teamId获取team状态信息
     * @param teamId
     */
    Byte getTeamStatusById(Long teamId);

    /**
     * @author ljy
     * 获取当前所有的serial
     * @param teamId
     * @param status
     */
    void setStatus(@Param("teamId") Long teamId,@Param("status") Byte status);

    /**ljy
     * 根据teamId获取组内学生id
     * @param teamId
     * @return
     */
    ArrayList<Long> getStudentsByTeamId(Long teamId);

    /**
     * 新建小组申请
     * @param
     */
    public void setTeamStatus(Long teamId);
}
