package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.AttendanceDao;
import com.gugu.gugumodel.dao.SeminarDao;
import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeamDao;
import com.gugu.gugumodel.entity.AttendanceEntity;
import com.gugu.gugumodel.entity.FileEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.guguuser.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class AttendanceService {
    @Autowired
    AttendanceDao attendanceDao;

    @Autowired
    TeamDao teamDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    SeminarDao seminarDao;

    @Autowired
    EmailUtil emailUtil;

    /**
     * 修改展示的轮次
     * @param attendanceId
     * @param teamOrder
     * @throws NotFoundException
     */
    public void editAttendance(Long attendanceId,Byte teamOrder) throws NotFoundException {
        attendanceDao.editAttendanceOrder(teamOrder,attendanceId);
    }

    /**
     * 删除展示
     * @param attendanceId
     * @throws NotFoundException
     */
    public void deleteAttendance(Long attendanceId) throws NotFoundException {
        attendanceDao.deleteAttendance(attendanceId);
    }

    /**
     * 上传文件刷新数据库记录
     * @param attendanceId
     * @param fileName
     * @param path
     * @throws NotFoundException
     */
    public void uploadReport(Long attendanceId,String fileName,String path) throws NotFoundException {
        attendanceDao.updloadReport(path,fileName,attendanceId);
    }

    /**
     * 上传PPT
     * @param attendanceId
     * @param fileName
     * @param path
     * @throws NotFoundException
     */
    public void uploadPPT(Long attendanceId,String fileName,String path) throws NotFoundException {
        attendanceDao.updloadPPT(path,fileName,attendanceId);
    }

    /**
     * 获取报告信息
     * @param attendanceId
     * @return
     */
    public FileEntity getReport(Long attendanceId){
        return attendanceDao.getReportPath(attendanceId);
    }
    /**
     * 获取ppt信息
     */
    public FileEntity getPpt(Long attendanceId){
        return attendanceDao.getPptPath(attendanceId);
    }

    /**
     * 获取报名讨论课的小组列表
     * @param klassSeminarId
     * @return
     */
    public ArrayList<AttendanceEntity> getBySeminarKlassId(Long klassSeminarId){
        return attendanceDao.getBySeminarKlassId(klassSeminarId);
    }

    /**ljy
     * 报名展示
     * @param attendanceEntity
     * @return
     */
    public Long newAttendance(AttendanceEntity attendanceEntity) throws SQLException {
        Long attendanceId= attendanceDao.newAttendance(attendanceEntity);
        if(attendanceId!=null){
            ArrayList<Long> studentIds=teamDao.getStudentsByTeamId(attendanceEntity.getTeamId());
            ArrayList<String> studentEmails=new ArrayList<String>();
            for(int i=0;i<studentIds.size();i++){
                String email=studentDao.getEmailById(studentIds.get(i));
                if(email!=null){
                    studentEmails.add(email);
                }

            }
            String topic=new String("讨论课报名");
            String seminarName=seminarDao.getSeminarName(attendanceEntity.getKlassSeminarId());
            String content=new String("您的小组成功报名讨论课："+seminarName);
//            emailUtil.sendSimpleEmail(topic,content,studentEmails);
        }
        return attendanceId;
    }

    /**
     * 给报告打分
     * @param courseId
     * @param roundId
     * @param klassSeminarId
     * @param teamId
     * @param score
     */
    public void setReportScore(Long courseId,Long roundId,Long klassSeminarId,Long teamId,Float score){
        attendanceDao.setSeminarScore(roundId,klassSeminarId,teamId,score,1,courseId);
    }

    /**
     * 给展示打分
     * @param courseId
     * @param roundId
     * @param klassSeminarId
     * @param teamId
     * @param score
     */
    public void setPresentationScore(Long courseId,Long roundId,Long klassSeminarId,Long teamId,Float score){
        attendanceDao.setSeminarScore(roundId,klassSeminarId,teamId,score,2,courseId);
    }

}
