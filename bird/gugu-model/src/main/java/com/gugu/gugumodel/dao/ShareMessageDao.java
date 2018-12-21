package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.ShareSeminarMapper;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import com.gugu.gugumodel.mapper.ShareTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Component
public class ShareMessageDao {

    @Autowired
    ShareSeminarMapper shareSeminarMapper;

    @Autowired
    ShareTeamMapper shareTeamMapper;

    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareList(Long userId){
        ArrayList<ShareApplicationEntity> shareSeminarList=shareSeminarMapper.getSeminarShareList(userId);
        for(int i=0;i<shareSeminarList.size();i++){
            Byte status=1;
            shareSeminarList.get(i).setStatus(status);
        }
        return shareSeminarList;
    }

    /**
     * 获得共享分组信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getTeamShareList(Long userId){
        ArrayList<ShareApplicationEntity> shareTeamList=shareTeamMapper.getTeamShareList(userId);
        for(int i=0;i<shareTeamList.size();i++){
            Byte status=2;
            shareTeamList.get(i).setStatus(status);
        }
        return shareTeamList;
    }
}
