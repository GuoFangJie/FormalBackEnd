package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.ShareApplicationEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import com.gugu.gugumodel.mapper.ShareSeminarMapper;
import com.gugu.gugumodel.mapper.ShareTeamMapper;
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


    /**
     * 教师获得组队申请信息
     * @param teacherId
     * @return
     */
    public ArrayList<TeamValidEntity> getTeamRequestList(Long teacherId){
        return teamValidRequestMapper.getTeamRequestList(teacherId);
    }

}
