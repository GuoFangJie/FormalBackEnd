package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.exception.NotFoundException;
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
    KlassDao klassDao;

    @Autowired
    RoundDao roundDao;

    @Autowired
    SeminarDao seminarDao;

    @Autowired
    KlassStudentDao klassStudentDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    TeamDao teamDao;

    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<Map> getSeminarShareList(Long userId) throws NotFoundException{
        ArrayList<ShareApplicationEntity> seminarShareList=shareMessageDao.getSeminarShareList(userId);
        return this.produceShareRequest(seminarShareList);
    }

    /**
     * 同意共享讨论课申请
     * @param requestId
     * @return
     */
    public boolean acceptSeminarShare(Long requestId) throws NotFoundException{
        Byte status=1;
        //获取共享消息
        ShareApplicationEntity shareApplicationEntity=shareMessageDao.getSeminarShareApplicationById(requestId);
        if(shareApplicationEntity==null){
            throw new NotFoundException("没有找到相应的共享讨论课消息");
        }
        //获得主课程
        CourseEntity mainCourse=courseDao.getCourseById(shareApplicationEntity.getMainCourseId());
        //获得从课程
        CourseEntity subCourse=courseDao.getCourseById(shareApplicationEntity.getSubCourseId());
        if(mainCourse==null&&subCourse==null){
            throw new NotFoundException("没有找到相应的主课程/从课程");
        }
        //删除从课程的所有round
        roundDao.deleteAllRoundByCourseId(subCourse.getId());
        //删除从课程的所有seminar
        courseDao.deleteAllSeminarByCourseId(subCourse.getId());
        //获得主课程的所有round,并加入到从课程的klass_round表中
        ArrayList<RoundEntity> roundList=roundDao.getRoundMessageByCourseId(mainCourse.getId());
        for(int i=0;i<roundList.size();i++){
            klassDao.newKlassRound(roundList.get(i).getId(),subCourse.getId());
        }
        //获得主课程的所有seminar,并加入到从课程的klass_seminar表中
        ArrayList<SeminarEntity> seminarList=seminarDao.getSeminarByCourseId(mainCourse.getId());
        for(int i=0;i<seminarList.size();i++){
            klassDao.newKlassSeminar(seminarList.get(i).getId(),subCourse.getId());
        }
        //修改共享消息的状态
        shareMessageDao.changeSeminarShareStatus(requestId,status);
        //修改课程共享的状态
        courseDao.changeSeminarShareStatus(subCourse.getId(),mainCourse.getId());
        return true;
    }

    /**
     * 拒绝共享讨论课申请
     * @param requestId
     * @return
     */
    public boolean refuseSeminarShare(Long requestId) throws ParamErrorException,NotFoundException{
        Byte status=0;
        shareMessageDao.changeSeminarShareStatus(requestId,status);
        return true;
    }

    /**
     * 修改共享讨论课申请的状态
     * @param requestId
     * @param handleType
     * @return
     */
    public boolean changeSeminarShareStatus(Long requestId,String handleType) throws ParamErrorException,NotFoundException{
        Byte status;
        if(handleType.equals("accept")){
            status=1;
            //获取共享消息
            ShareApplicationEntity shareApplicationEntity=shareMessageDao.getSeminarShareApplicationById(requestId);
            if(shareApplicationEntity==null){
                throw new NotFoundException("没有找到相应的共享讨论课消息");
            }
            //获得主课程
            CourseEntity mainCourse=courseDao.getCourseById(shareApplicationEntity.getMainCourseId());
            //获得从课程
            CourseEntity subCourse=courseDao.getCourseById(shareApplicationEntity.getSubCourseId());
            if(mainCourse==null&&subCourse==null){
                throw new NotFoundException("没有找到相应的主课程/从课程");
            }
            //删除从课程的所有round
            roundDao.deleteAllRoundByCourseId(subCourse.getId());
            //删除从课程的所有seminar
            courseDao.deleteAllSeminarByCourseId(subCourse.getId());
            //获得主课程的所有round,并加入到从课程的klass_round表中
            ArrayList<RoundEntity> roundList=roundDao.getRoundMessageByCourseId(mainCourse.getId());
            for(int i=0;i<roundList.size();i++){
                klassDao.newKlassRound(roundList.get(i).getId(),subCourse.getId());
            }
            //获得主课程的所有seminar,并加入到从课程的klass_seminar表中
            ArrayList<SeminarEntity> seminarList=seminarDao.getSeminarByCourseId(mainCourse.getId());
            for(int i=0;i<seminarList.size();i++){
                klassDao.newKlassSeminar(seminarList.get(i).getId(),subCourse.getId());
            }
            //修改共享消息的状态
            shareMessageDao.changeSeminarShareStatus(requestId,status);
            //修改课程共享的状态
            courseDao.changeSeminarShareStatus(subCourse.getId(),mainCourse.getId());
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
    public ArrayList<Map> getTeamShareList(Long userId) throws NotFoundException{
        ArrayList<ShareApplicationEntity> teamShareList=shareMessageDao.getTeamShareList(userId);
        return this.produceShareRequest(teamShareList);
    }

    /**
     * 修改共享组队申请的状态
     * @param requestId
     * @param handleType
     * @return
     */
    public boolean changeTeamShareStatus(Long requestId,String handleType) throws ParamErrorException,NotFoundException{
        Byte status;
        if(handleType.equals("accept")){
            status=1;
            //获取共享讨论课消息
            ShareApplicationEntity shareApplicationEntity=shareMessageDao.getTeamShareApplicationById(requestId);
            if(shareApplicationEntity==null){
                throw new NotFoundException("没有找到相应的共享讨论课消息");
            }
            //获得主课程
            CourseEntity mainCourse=courseDao.getCourseById(shareApplicationEntity.getMainCourseId());
            //获得从课程
            CourseEntity subCourse=courseDao.getCourseById(shareApplicationEntity.getSubCourseId());
            if(mainCourse==null&&subCourse==null){
                throw new NotFoundException("没有找到相应的主课程/从课程");
            }
            //删除从课程的所有team
            teamDao.deleteAllTeamByCourseId(subCourse.getId());
            //获得所有主课程的team
            ArrayList<TeamEntity> teamList=teamDao.getAllTeamByCourseId(mainCourse.getId());
            //在klass_team中创建副本
            for(int i=0;i<teamList.size();i++){
                this.teamChangeCourse(teamList.get(i),mainCourse,subCourse);
            }
            //修改课程共享的状态
            courseDao.changeTeamShareStatus(subCourse.getId(),mainCourse.getId());
        }
        else if(handleType.equals("refuse")){
            status=0;
        }
        else{
            throw new ParamErrorException("请求参数错误（必须为accept/refuse）");
        }
        //修改申请信息的状态
        shareMessageDao.changeTeamShareStatus(requestId,status);
        return true;
    }


    /**
     * 在klass_team中创建副本
     * @param teamEntity
     * @param mainCourse
     * @param subCourse
     * @return
     */
    private void teamChangeCourse(TeamEntity teamEntity,CourseEntity mainCourse,CourseEntity subCourse){
        //获取该组所有的学生
        ArrayList<Long> studentList=klassStudentDao.getStudentByTeamId(teamEntity.getId());
        //多数学生所在的klassId
        Long maxKlassId=null;
        Map<Long,Integer> klassMap=new HashMap<>();
        Integer temp=0;
        //对小组内每个学生进行操作
        for(int i=0;i<studentList.size();i++){
            //获取这个学生的entity
            StudentEntity student=studentDao.getStudentById(studentList.get(i));
            //获得这个学生的klassId
            Long klassId=klassStudentDao.getKlassIdByCourseAndStudent(subCourse.getId(),student.getId());
            //收集每个学生的klassId
            if(klassId!=null){
                Integer klassCount=klassMap.get(klassId);
                if(klassCount==null){
                    klassMap.put(klassId,1);
                }
                else{
                    klassMap.put(klassId,klassCount+1);
                }
            }
        }
        //取出人数最多的klassId
        for (Long key : klassMap.keySet()) {
            Integer value=klassMap.get(key);
            if(value>temp){
                temp=value;
                maxKlassId=key;
            }
        }
        //创建新的klass_team的副本
        if(maxKlassId!=null){
            teamDao.createKlassTeam(maxKlassId,teamEntity.getId());
        }
    }


    /**
     * 获取该课程所有相关的共享信息
     * @param courseId
     * @return
     */
    public ArrayList<Map> getShareListByCourseId(Long courseId){
        ArrayList<ShareApplicationEntity> seminarShareList = shareMessageDao.getSeminarShareListByCourseId(courseId);
        ArrayList<ShareApplicationEntity> teamShareList = shareMessageDao.getTeamShareListByCourseId(courseId);
        ArrayList<Map> shareList=new ArrayList<Map>();
        if(seminarShareList.size()==0&&teamShareList.size()==0){
            return null;
        }
        for(int i=0;i<seminarShareList.size();i++){
            ShareApplicationEntity seminarShare=seminarShareList.get(i);
            shareList.add(this.getSeminarShareInfo(seminarShare,courseId));
        }
        for(int i=0;i<teamShareList.size();i++){
            ShareApplicationEntity teamShare=teamShareList.get(i);
            shareList.add(this.getTeamShareInfo(teamShare,courseId));
        }
        return shareList;
    }

    /**
     * 获取共享讨论课信息
     * @param seminarShare
     * @param courseId
     * @return
     */
    private Map getSeminarShareInfo(ShareApplicationEntity seminarShare,Long courseId){
        Map seminarMap=new HashMap();
        if(seminarShare.getMainCourseId().equals(courseId)){
            //如果是主课程,获取从课程
            CourseEntity subCourse=courseDao.getCourseById(seminarShare.getSubCourseId());
            seminarMap.put("shareId",seminarShare.getId());
            seminarMap.put("courseName",subCourse.getCourseName());
            seminarMap.put("shareType","共享讨论课");
            seminarMap.put("isMain","主课程");
        }
        else if(seminarShare.getSubCourseId().equals(courseId)){
            //如果是从课程,获取主课程
            CourseEntity mainCourse=courseDao.getCourseById(seminarShare.getMainCourseId());
            seminarMap.put("shareId",seminarShare.getId());
            seminarMap.put("courseName",mainCourse.getCourseName());
            seminarMap.put("shareType","共享讨论课");
            seminarMap.put("isMain","从课程");
        }
        return seminarMap;
    }

    /**
     * 获取共享分组信息
     * @param teamShare
     * @param courseId
     * @return
     */
    private Map getTeamShareInfo(ShareApplicationEntity teamShare,Long courseId){
        Map teamMap=new HashMap();
        if(teamShare.getMainCourseId().equals(courseId)){
            //如果是主课程,获取从课程
            CourseEntity subCourse=courseDao.getCourseById(teamShare.getSubCourseId());
            teamMap.put("shareId",teamShare.getId());
            teamMap.put("courseName",subCourse.getCourseName());
            teamMap.put("shareType","共享分组");
            teamMap.put("isMain","主课程");
        }
        else if(teamShare.getSubCourseId().equals(courseId)){
            //如果是从课程,获取主课程
            CourseEntity mainCourse=courseDao.getCourseById(teamShare.getMainCourseId());
            teamMap.put("shareId",teamShare.getId());
            teamMap.put("courseName",mainCourse.getCourseName());
            teamMap.put("shareType","共享分组");
            teamMap.put("isMain","从课程");
        }
        return teamMap;
    }

    /**
     * 生成申请信息
     * @param shareList
     * @return
     */
    private ArrayList<Map> produceShareRequest(ArrayList<ShareApplicationEntity> shareList) throws NotFoundException{
        ArrayList <Map> shareRequestList=new ArrayList<Map>();
        for(int i=0;i<shareList.size();i++){
            Map shareRequest = new HashMap();
            ShareApplicationEntity shareApplication=shareList.get(i);
            if(shareApplication==null){
                throw new NotFoundException("找不到相应的共享申请");
            }
            CourseEntity mainCourse=courseDao.getCourseById(shareApplication.getMainCourseId());
            CourseEntity subCourse=courseDao.getCourseById(shareApplication.getSubCourseId());
            if(mainCourse==null&&subCourse==null){
                throw new NotFoundException("找不到主课程/从课程");
            }
            TeacherEntity mainTeacher=teacherDao.getTeacherById(mainCourse.getTeacherId());
            if(mainTeacher==null){
                throw new NotFoundException("找不到主课程的教师");
            }
            shareRequest.put("requestId",shareApplication.getId());
            String mainCourseName=mainCourse.getCourseName();
            shareRequest.put("mainCourseId",shareApplication.getMainCourseId());
            shareRequest.put("mainCourseName",mainCourseName);
            String subCourseName=subCourse.getCourseName();
            shareRequest.put("subCourseId",shareApplication.getSubCourseId());
            shareRequest.put("subCourseName",subCourseName);
            String mainTeacherName=mainTeacher.getTeacherName();
            shareRequest.put("mainTeacherId",shareApplication.getSubCourseTeacherId());
            shareRequest.put("mainTeacherName",mainTeacherName);
            shareRequest.put("type",shareApplication.getType());
            shareRequestList.add(shareRequest);
        }
        return shareRequestList;
    }
}
