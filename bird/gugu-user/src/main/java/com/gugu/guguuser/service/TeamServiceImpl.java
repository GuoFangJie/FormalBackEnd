package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.pojo.entity.*;
import com.gugu.gugumodel.pojo.vo.TeamMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDao teamDao;

    @Autowired
    StudentDao studentDao;

    @Override
    public TeamEntity getTeamMessageByTeamId(Long teamId){
        return teamDao.getTeamById(teamId);
    }

    @Override
    public StudentEntity getLeaderByTeamId(Long teamId){
        return studentDao.getLeader(teamId);
    }

    @Override
    public ArrayList<StudentEntity> getMemberById(Long teamId){
        return studentDao.getMembersExceptLeader(teamId);
    }

    public void updateTeam(TeamEntity teamEntity,ArrayList<StudentEntity> memberStudents){

        //更新team表信息
        teamDao.updateTeam(teamEntity);
        //删除之前小组和组员的联系
        teamDao.deleteStudentFromTeam(teamEntity.getId());
        //新建现在小组和组员的联系
        for(int i=0;i<memberStudents.size();i++){
            teamDao.buildRelationStuAndTeam(memberStudents.get(i).getId(),teamEntity);
        }
    }

    public void deleteTeam(Long teamId){
        teamDao.deleteTeam(teamId);
        teamDao.deleteStudentTeamRelation(teamId);
    }

    public void addMember(Long teamId,Long studentId){
        teamDao.addMember(teamId,studentId);
    }

    public void removeMember(Long teamId,Long studentId){
        teamDao.removeMember(teamId,studentId);
    }
}
