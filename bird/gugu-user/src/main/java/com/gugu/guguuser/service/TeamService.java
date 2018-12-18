package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.pojo.entity.*;
import com.gugu.gugumodel.pojo.vo.TeamMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeamService {
    @Autowired
    TeamDao teamDao;

    @Autowired
    StudentDao studentDao;


    /**
     * 获取小组信息
     * @param teamId
     * @return
     */
    public TeamEntity getTeamMessageByTeamId(Long teamId){
        return teamDao.getTeamById(teamId);
    }

    /**
     * 获取小组队长
     * @param teamId
     * @return
     */
    public StudentEntity getLeaderByTeamId(Long teamId){
        return studentDao.getLeader(teamId);
    }

    /**
     * 获取小组成员
     * @param teamId
     * @return
     */
    ArrayList<StudentEntity> getMemberById(Long teamId);


    public ArrayList<StudentEntity> getMemberById(Long teamId){
        return studentDao.getMembersExceptLeader(teamId);
    }
}
