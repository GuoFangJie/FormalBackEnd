package com.gugu.guguuser.service;

import com.gugu.gugumodel.pojo.entity.*;
import com.gugu.gugumodel.pojo.vo.TeamMessageVO;

import java.util.ArrayList;

/**
 * @author ljy
 */
public interface TeamService {
    /**
     * 新建队伍
     * @param teamMessageVO
     */
   // void newTeam(TeamMessageVO teamMessageVO);


    /**
     * 获取team信息
     * @param teamId
     */
    TeamEntity getTeamMessageByTeamId(Long teamId);

    /**
     * 获取小组组长信息
     * @param teamId
     */
    StudentEntity getLeaderByTeamId(Long teamId);

    /**
     * 获取小组组长信息
     * @param teamId
     */
    ArrayList<StudentEntity> getMemberById(Long teamId);
}
