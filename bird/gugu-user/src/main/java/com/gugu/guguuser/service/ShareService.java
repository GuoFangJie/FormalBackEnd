package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.ShareMessageDao;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Service
public class ShareService {

    @Autowired
    ShareMessageDao shareMessageDao;

    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareList(Long userId){
        return shareMessageDao.getSeminarShareList(userId);
    }

    /**
     * 获得共享组队信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getTeamShareList(Long userId){
        return shareMessageDao.getTeamShareList(userId);
    }
}
