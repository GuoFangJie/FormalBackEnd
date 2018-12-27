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


    /**ljy
     * 报名展示
     * @param attendanceEntity
     * @return
     */
    public Long newAttendance(AttendanceEntity attendanceEntity);

    /**
     * 提交展示成绩
     * @param score
     * @param klassSeminarId
     * @param teamId
     */
    void editPresentationScore(Long score,Long klassSeminarId,Long teamId);

    /**
     * 提交报告成绩
     * @param score
     * @param klassSeminarId
     * @param teamId
     */
    void editReportScore(Long score,Long klassSeminarId,Long teamId);

    /**
     * 设置总分
     * @param score
     * @param klassSeminarId
     * @param teamId
     */
    void editTotlaScore(Long score,Long klassSeminarId,Long teamId);


    /**
     * 根据讨论课和座次获取展示小组
     * @param teamOrder
     * @param klassSeminarId
     * @return
     */
    AttendanceEntity getAttendanceByTeamOrder(Byte teamOrder,Long klassSeminarId);
}
