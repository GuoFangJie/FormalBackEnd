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
            //获得主课程
            CourseEntity mainCourse=courseDao.getCourseById(shareApplicationEntity.getMainCourseId());
            //获得从课程
            CourseEntity subCourse=courseDao.getCourseById(shareApplicationEntity.getSubCourseId());
            //删除从课程的所有round
            roundDao.deleteAllRoundByCourseId(shareApplicationEntity.getSubCourseId());
            //删除从课程的所有seminar
            courseDao.deleteAllSeminarByCourseId(shareApplicationEntity.getSubCourseId());
            //获得主课程的所有seminar,并加入到从课程的klass_seminar表中
            ArrayList<SeminarEntity> seminarList=seminarDao.getSeminarByCourseId(mainCourse.getId());
            for(int i=0;i<seminarList.size();i++){
                klassDao.newKlassSeminar(seminarList.get(i).getId(),subCourse.getId());
            }
            //获得主课程的所有round,并加入到从课程的klass_round表中
            ArrayList<RoundEntity> roundList=roundDao.getRoundMessageByCourseId(subCourse.getId());
            for(int i=0;i<roundList.size();i++){
                klassDao.newKlassRound(roundList.get(i).getId(),subCourse.getId());
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
    public boolean changeTeamShareStatus(Long requestId,String handleType) throws ParamErrorException{
        Byte status;
        if(handleType.equals("accept")){
            status=1;
            ShareApplicationEntity shareApplicationEntity=shareMessageDao.getTeamShareApplicationById(requestId);
            //获得主课程
            CourseEntity mainCourse=courseDao.getCourseById(shareApplicationEntity.getMainCourseId());
            //获得从课程
            CourseEntity subCourse=courseDao.getCourseById(shareApplicationEntity.getSubCourseId());
            //删除从课程的所有team
            teamDao.deleteAllTeamByCourseId(subCourse.getId());
            //获得所有主课程的team
            ArrayList<TeamEntity> teamList=teamDao.getAllTeamByCourseId(mainCourse.getId());
            //转换team的课程
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
     * 生成申请信息
     * @param shareList
     * @return
     */
    private ArrayList<Map> produceShareRequest(ArrayList<ShareApplicationEntity> shareList) throws NotFoundException{
        ArrayList <Map> shareRequestList=new ArrayList<Map>();
        for(int i=0;i< shareList.size();i++){
            Map shareRequest = new HashMap();
            ShareApplicationEntity shareApplication=shareList.get(i);
            System.out.println(shareApplication);
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

    /**
     * 转换小组的课程
     * @param teamEntity
     * @param mainCourse
     * @param subCourse
     * @return
     */
    private void teamChangeCourse(TeamEntity teamEntity,CourseEntity mainCourse,CourseEntity subCourse){
        ArrayList<Long> studentList=klassStudentDao.getStudentByTeamId(mainCourse.getId());
        ArrayList<Long> newStudentList=new ArrayList<Long>();
        //多数学生所在的klassId
        Long maxKlassId=null;
        for(int i=0;i<studentList.size();i++){
            Map<Long,Integer> klassMap=new HashMap<>();
            Integer temp=0;
            StudentEntity student=studentDao.getStudentById(studentList.get(i));
            //获得这个学生的klassId
            Long klassId=klassStudentDao.getKlassIdByCourseAndStudent(student.getId(),subCourse.getId());
            //收集每个学生的klassId
            if(klassId!=null){
                Integer klassCount=klassMap.get(klassId);
                newStudentList.add(klassId);
                if(klassCount==null){
                    klassMap.put(klassId,1);
                }
                else{
                    klassMap.put(klassId,klassCount+1);
                }
            }
            //取出人数最多的klassId
            for (Long key : klassMap.keySet()) {
                Integer value=klassMap.get(key);
                temp=(value>temp)?value:temp;
                maxKlassId=key;
            }
        }
        //设置新组的klassId为队伍里人数最多的klassId
        teamEntity.setKlassId(maxKlassId);
        //设置新组的courseId为从课程的courseId
        teamEntity.setCourseId(subCourse.getId());
        //创建新组的副本
        Long newTeamId=teamDao.createTeam(teamEntity);
        this.setNewTeamByStudentId(newStudentList,newTeamId);
    }

    /**
     * 设置学生的新team
     * @param studentList
     * @param teamId
     * @return
     */
    private void setNewTeamByStudentId(ArrayList<Long> studentList,Long teamId){
        for(int i=0;i<studentList.size();i++){
            klassStudentDao.updateTeamByStudentId(studentList.get(i),teamId);
        }
    }

}
