package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDao;
import com.gugu.gugumodel.dao.KlassDao;
import com.gugu.gugumodel.dao.ShareMessageDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TYJ
 */
@Service
public class ShareService {

    @Autowired
    ShareMessageDao shareMessageDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    KlassDao klassDao;

    @Autowired
    TeacherDao teacherDao;


    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<Map> getSeminarShareList(Long userId){
        ArrayList<ShareApplicationEntity> seminarShareList=shareMessageDao.getSeminarShareList(userId);
        return this.produceShareRequest(seminarShareList);
    }

    /**
     * 获得共享组队信息
     * @param userId
     * @return
     */
    public ArrayList<Map> getTeamShareList(Long userId){
        ArrayList<ShareApplicationEntity> teamShareList=shareMessageDao.getTeamShareList(userId);
        return this.produceShareRequest(teamShareList);
    }

    /**
     * 生成申请信息
     * @param shareList
     * @return
     */
    private ArrayList<Map> produceShareRequest(ArrayList<ShareApplicationEntity> shareList){
        ArrayList <Map> shareRequestList=new ArrayList<Map>();
        for(int i=0;i< shareList.size();i++){
            Map shareRequest = new HashMap();
            ShareApplicationEntity shareApplication=shareList.get(i);
            shareRequest.put("requestId",shareApplication.getId());
            CourseEntity mainCourse=courseDao.getCourseById(shareApplication.getMainCourseId());
            CourseEntity subCourse=courseDao.getCourseById(shareApplication.getSubCourseId());
            String mainCourseName=mainCourse.getCourseName();
            shareRequest.put("mainCourseId",shareApplication.getMainCourseId());
            shareRequest.put("mainCourseName",mainCourseName);
            String subCourseName=subCourse.getCourseName();
            shareRequest.put("subCourseId",shareApplication.getSubCourseId());
            shareRequest.put("subCourseName",subCourseName);
            String mainTeacherName=teacherDao.getTeacherById(mainCourse.getTeacherId()).getTeacherName();
            shareRequest.put("mainTeacherId",shareApplication.getSubCourseTeacherId());
            shareRequest.put("mainTeacherName",mainTeacherName);
            shareRequest.put("type",shareApplication.getType());
            shareRequest.put("status",shareApplication.getStatus());
            shareRequestList.add(shareRequest);
        }
        return shareRequestList;
    }
}
