package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.TeamRequestDao;
import com.gugu.gugumodel.entity.TeamValidEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Service
public class TeamRequestService {

    @Autowired
    TeamRequestDao teamMessageDao;

    /**
     * 获得共享讨论课信息
     * @param teacherId
     * @return
     */
    public ArrayList<TeamValidEntity> getTeamRequestList(Long teacherId){
        return teamMessageDao.getTeamRequestList(teacherId);
    }

}
