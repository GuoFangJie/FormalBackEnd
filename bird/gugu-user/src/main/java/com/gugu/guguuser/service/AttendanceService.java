package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.AttendanceDao;
import com.gugu.gugumodel.entity.AttendanceEntity;
import com.gugu.gugumodel.entity.FileEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ren
 */
@Service
public class AttendanceService {
    @Autowired
    AttendanceDao attendanceDao;

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
}
