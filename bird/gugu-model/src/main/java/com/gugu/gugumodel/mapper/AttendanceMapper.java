package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.AttendanceEntity;
import com.gugu.gugumodel.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
@Mapper
public interface AttendanceMapper {
    /**
     * 修改attendance的顺序
     * @param teamOrder
     * @param attendanceId
     */
    void editTeamOrder(Byte teamOrder,Long attendanceId);

    /**
     * 根据id获取attendance记录
     * @param attendanceId
     * @return
     */
    AttendanceEntity getById(Long attendanceId);

    /**
     * 根据id删除展示记录
     * @param id
     */
    void deleteById(Long id);

    /**
     * 上传报告
     * @param path
     * @param fileName
     * @param attendanceId
     */
    void uploadReport(String path,String fileName,Long attendanceId);

    /**
     * 上传PPT
     * @param path
     * @param fileName
     * @param attendanceId
     */
    void uploadPPT(String path,String fileName,Long attendanceId);

    /**
     * 获取报告文件的信息
     * @param attendanceId
     * @return
     */
   FileEntity getReportPath(Long attendanceId);

    /**
     * 获取PPT文件的信息
     * @param attendanceId
     * @return
     */
   FileEntity getPptPath(Long attendanceId);

    /**
     * 获取报名讨论课的展示信息
     * @param seminarKlassId
     * @return
     */
   ArrayList<AttendanceEntity> getBySeminarKlassId(Long seminarKlassId);
}
