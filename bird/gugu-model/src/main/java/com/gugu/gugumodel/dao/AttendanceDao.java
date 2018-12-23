package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.AttendanceEntity;
import com.gugu.gugumodel.entity.FileEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.AttendanceMapper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class AttendanceDao {
    @Autowired
    AttendanceMapper attendanceMapper;

    /**
     * 修改展示的顺序
     * @param teamOrder
     * @param attendanceId
     * @throws NotFoundException
     */
    public void editAttendanceOrder(Byte teamOrder,Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("记录不存在");
        }else{
            attendanceMapper.editTeamOrder(teamOrder,attendanceId);
        }
    }

    /**
     * 删除展示
     * @param attendanceId
     * @throws NotFoundException
     */
    public void deleteAttendance(Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("记录不存在");
        }else{
            attendanceMapper.deleteById(attendanceId);
        }
    }

    /**
     * 修改报告文件的路径
     * @param path
     * @param fileName
     * @param attendanceId
     * @throws NotFoundException
     */
    public void updloadReport(String path,String fileName,Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("数据不存在");
        }else{
            attendanceMapper.uploadReport(path,fileName,attendanceId);
        }
    }

    /**
     * 上传PPT
     * @param path
     * @param fileName
     * @param attendanceId
     * @throws NotFoundException
     */
    public void updloadPPT(String path,String fileName,Long attendanceId) throws NotFoundException {
        if(attendanceMapper.getById(attendanceId)==null){
            throw new NotFoundException("数据不存在");
        }else{
            attendanceMapper.uploadPPT(path,fileName,attendanceId);
        }
    }

    /**
     * 获取报告文件信息
     * @param attendanceId
     * @return
     */
    public FileEntity getReportPath(Long attendanceId){
        return attendanceMapper.getReportPath(attendanceId);
    }

    /**
     * 获取ppt文件信息
     * @param attendanceId
     * @return
     */
    public FileEntity getPptPath(Long attendanceId){
        return attendanceMapper.getPptPath(attendanceId);
    }

    /**
     * 根据id获取展示的详细信息
     */
    public AttendanceEntity getById(Long attendanceId){
        return attendanceMapper.getById(attendanceId);
    }
    /**
     * 获取某次讨论课报名小组列表
     */
    public ArrayList<AttendanceEntity> getBySeminarKlassId(Long seminarKlassId){
        return attendanceMapper.getBySeminarKlassId(seminarKlassId);
    }

    /**ljy
     * 报名展示
     * @param attendanceEntity
     * @return
     */
    public Long newAttendance(AttendanceEntity attendanceEntity){
        attendanceMapper.newAttendance(attendanceEntity);
        return attendanceEntity.getId();
    }
}
