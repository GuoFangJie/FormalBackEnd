package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.CourseMapper;
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

    @Autowired
    CourseMapper courseMapper;

    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareList(Long userId){
        ArrayList<ShareApplicationEntity> shareSeminarList=shareSeminarMapper.getSeminarShareList(userId);
        for(int i=0;i<shareSeminarList.size();i++){
            Byte type=1;
            shareSeminarList.get(i).setType(type);
        }
        return shareSeminarList;
    }

    /**
     * 根据ID获取共享请求信息
     * @param requestId
     * @return
     */
    public ShareApplicationEntity getSeminarShareApplicationById(Long requestId){
        return shareSeminarMapper.getSeminarShareApplicationById(requestId);
    }

    /**
     * 修改共享讨论课申请的状态
     * @param requestId
     * @param status
     * @return
     */
    public void changeSeminarShareStatus(Long requestId,Byte status){
        shareSeminarMapper.changeSeminarShareStatus(requestId,status);
    }

    /**
     * 获得共享分组信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getTeamShareList(Long userId){
        ArrayList<ShareApplicationEntity> shareTeamList=shareTeamMapper.getTeamShareList(userId);
        for(int i=0;i<shareTeamList.size();i++){
            Byte type=2;
            shareTeamList.get(i).setType(type);
        }
        return shareTeamList;
    }

    /**
     * 根据ID获取共享分组请求信息
     * @param requestId
     * @return
     */
    public ShareApplicationEntity getTeamShareApplicationById(Long requestId){
        return shareTeamMapper.getTeamShareApplicationById(requestId);
    }

    /**
     * 修改共享分组申请的状态
     * @param requestId
     * @param status
     * @return
     */
    public void changeTeamShareStatus(Long requestId,Byte status){
        shareTeamMapper.changeTeamShareStatus(requestId,status);
    }

    /**
     * 新建共享申请
     * 1 为共享讨论课
     * 2为共享分组
     * @param mainCourseId
     * @param subCourseId
     */
    public void newShareSeminarApplication(Long mainCourseId,Long subCourseId,Integer type){
        Long subCourseTeacher=courseMapper.getTeacherIdByCourse(subCourseId);
        if(type.equals(1)){
            shareSeminarMapper.newShareSeminarApplication(mainCourseId,subCourseId,subCourseTeacher);
        }else{
            shareTeamMapper.newShareTeamApplication(mainCourseId,subCourseId,subCourseTeacher);
        }
    }
}
