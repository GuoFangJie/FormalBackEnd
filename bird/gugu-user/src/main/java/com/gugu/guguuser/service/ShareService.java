package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import com.gugu.gugumodel.exception.ParamErrorException;
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
    SeminarDao seminarDao;

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
     * 修改共享讨论课申请的状态
     * @param requestId
     * @param handleType
     * @return
     */
    public boolean changeSeminarShareStatus(Long requestId,String handleType) throws ParamErrorException{
        Byte status;
        if(handleType.equals("accept")){
            status=1;
            ShareApplicationEntity shareApplicationEntity=shareMessageDao.getSeminarShareApplicationById(requestId);
            CourseEntity mainCourse=courseDao.getCourseById(shareApplicationEntity.getMainCourseId());
            courseDao.deleteAllSeminarByCourseId(shareApplicationEntity.getSubCourseId());
            ArrayList<SeminarEntity> seminarList=seminarDao.getSeminarByCourseId(mainCourse.getId());
            //然后插入到klass_seminar表中
            shareMessageDao.changeSeminarShareStatus(requestId,status);
        }
        else if(handleType.equals("refuse")){
            status=0;
            shareMessageDao.changeSeminarShareStatus(requestId,status);
        }
        else{
            throw new ParamErrorException("请求参数错误（必须为accept/refuse）");
        }
        return true;
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
