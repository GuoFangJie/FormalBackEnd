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
        ArrayList<ShareApplicationEntity> realList=new ArrayList<>();
        for(int i=0;i<shareSeminarList.size();i++){
            if(shareSeminarList.get(i).getStatus()==null){
                Byte type=1;
                shareSeminarList.get(i).setType(type);
                realList.add(shareSeminarList.get(i));
            }
        }
        return realList;
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
        ArrayList<ShareApplicationEntity> realList=new ArrayList<>();
        for(int i=0;i<shareTeamList.size();i++){
            if(shareTeamList.get(i).getStatus()==null){
                Byte type=2;
                shareTeamList.get(i).setType(type);
                realList.add(shareTeamList.get(i));
            }
        }
        return realList;
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
     * 2 为共享分组
     * @param mainCourseId
     * @param subCourseId
     */
    public void newShareSeminarApplication(Long mainCourseId,Long subCourseId,Integer type) throws Exception {
        Long subCourseTeacher=courseMapper.getTeacherIdByCourse(subCourseId);
        if(type.equals(1)){
            System.out.println("type为1");
            ShareApplicationEntity shareApplicationEntity=shareSeminarMapper.getByMainCourseAndSubcourse(mainCourseId,subCourseId);
            if(shareApplicationEntity==null) {
                shareSeminarMapper.newShareSeminarApplication(mainCourseId, subCourseId, subCourseTeacher);
            }else if(shareApplicationEntity.getStatus()==1){
                throw new Exception("已申请过并成功");
            }else{
                shareSeminarMapper.changeSeminarShareStatus(shareApplicationEntity.getId(),null);
            }
        }else{
            ShareApplicationEntity shareApplicationEntity=shareTeamMapper.getByMainCourseAndSubcourse(mainCourseId,subCourseId);
            System.out.println("type为2");
            if(shareApplicationEntity==null) {
                shareTeamMapper.newShareTeamApplication(mainCourseId, subCourseId, subCourseTeacher);
            }else if(shareApplicationEntity.getStatus()==1){
                throw new Exception("已申请过并成功");
            }else{
                shareTeamMapper.changeTeamShareStatus(shareApplicationEntity.getId(),null);
            }
        }
    }

    /**
     * 获取该课程所有相关的共享讨论课信息
     * @param courseId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareListByCourseId(Long courseId){
        return shareSeminarMapper.getSeminarShareListByCourseId(courseId);
    }

    /**
     * 获取该课程所有相关的共享分组信息
     * @param courseId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getTeamShareListByCourseId(Long courseId){
        return shareTeamMapper.getTeamShareListByCourseId(courseId);
    }
}
