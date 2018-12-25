package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.ShareApplicationEntity;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.ShareSeminarMapper;
import com.gugu.gugumodel.mapper.ShareTeamMapper;
import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.mapper.TeamValidRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Component
public class TeamRequestDao {

    @Autowired
    TeamValidRequestMapper teamValidRequestMapper;

    @Autowired
    StudentMapper studentMapper;


    /**
     * 教师获得组队申请信息
     * @param teacherId
     * @return
     */
    public ArrayList<TeamValidEntity> getTeamRequestList(Long teacherId) throws NotFoundException{
        ArrayList<TeamValidEntity> teamRequestList=teamValidRequestMapper.getTeamRequestList(teacherId);
        for(int i=0;i<teamRequestList.size();i++){
            if(teamRequestList.get(i).getStatus()!=null){
                teamRequestList.remove(i);
            }
        }
        return teamRequestList;
    }

    /**
     * 根据ID获取组队申请信息
     * @param requestId
     * @return
     */
    public TeamValidEntity getTeamRequestById(Long requestId){
        return teamValidRequestMapper.getTeamRequestById(requestId);
    }

    /**
     * 修改组队申请的状态
     * @param requestId
     * @param status
     * @return
     */
    public void changeTeamRequestStatus(Long requestId,Byte status){
        teamValidRequestMapper.changeTeamRequestStatus(requestId,status);
    }

}
