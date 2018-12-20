package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.ShareMessageMapper;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Component
public class ShareMessageDao {

    @Autowired
    ShareMessageMapper shareMessageMapper;

    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareList(Long userId){
        return shareMessageMapper.getSeminarShareList(userId);
    }
}
