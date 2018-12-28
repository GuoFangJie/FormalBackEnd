package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.AttendanceEntity;
import com.gugu.gugumodel.entity.FileEntity;
import com.gugu.gugumodel.entity.SeminarScoreEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.AttendanceMapper;
import com.gugu.guguuser.controller.vo.AttendanceMessageVO;
import com.gugu.guguuser.service.AttendanceService;
import com.gugu.guguuser.service.SeminarService;
import com.gugu.guguuser.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ren
 */
@RequestMapping("attendance")
@RestController
public class AttendanceController {
    @Autowired
    AttendanceService attendanceService;
    @Value("${reportPathInServer}")
    String reportPathInServer;
    @Value("${reportPath}")
    String reportPath;
    @Autowired
    TeamService teamService;
    @Autowired
    SeminarService seminarService;

    /**
     * 修改展示顺序
     * @param httpServletResponse
     * @param attendance
     * @param teamOrder
     */
    @PutMapping("/{attendanceId}")
    @RolesAllowed("Student")
    public void editAttendance(HttpServletResponse httpServletResponse, @PathVariable("attendanceId") Long attendance, @RequestParam("teamOrder") Byte teamOrder){
        try {
            attendanceService.editAttendance(attendance,teamOrder);
        } catch (NotFoundException e) {
            httpServletResponse.setStatus(404,e.getErrorMsg());
        }
    }

    /**
     * 取消展示报名
     * @param httpServletResponse
     * @param attendanceId
     */
    @DeleteMapping("/{attendanceId}")
    @RolesAllowed("Student")
    public void deleteAttendance(HttpServletResponse httpServletResponse,@PathVariable("attendanceId")Long attendanceId){
        try {
            attendanceService.deleteAttendance(attendanceId);
        } catch (NotFoundException e) {
            httpServletResponse.setStatus(404,e.getErrorMsg());
        }
    }

    /**
     * 上传报告
     * @param httpServletResponse
     * @param attendanceId
     * @param file
     */
    @PostMapping("/{attendanceId}/report")
    @RolesAllowed("Student")
    public void updateReport(HttpServletResponse httpServletResponse,@PathVariable Long attendanceId, MultipartFile file){
        System.out.println("BEGIN");
        try {
            String path=file.getOriginalFilename();
            File dest=new File(reportPathInServer+path);

            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            file.transferTo(dest);
            attendanceService.uploadReport(attendanceId,path,reportPath+path);
        } catch (IOException e) {
            httpServletResponse.setStatus(302,"文件上传失败");
        }
        catch (NotFoundException e){
            httpServletResponse.setStatus(404,"不存在该展示记录");
        }
    }

    /**
     * 上传PPT
     * @param httpServletResponse
     * @param attendanceId
     * @param file
     */
    @PostMapping("/{attendanceId}/powerpoint")
    @RolesAllowed("Student")
    public void updatePPT(HttpServletResponse httpServletResponse,@PathVariable Long attendanceId, MultipartFile file){
        System.out.println("BEGIN");
        try {
            String path=file.getOriginalFilename();
            File dest=new File(reportPathInServer+path);

            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdir();
            }
            file.transferTo(dest);
            attendanceService.uploadPPT(attendanceId,path,reportPath+path);
        } catch (IOException e) {
            httpServletResponse.setStatus(302,"文件上传失败");
        }
        catch (NotFoundException e){
            httpServletResponse.setStatus(404,"不存在该展示记录");
        }
    }

    /**
     * 获取报告信息
     * @param attendanceId
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/{attendanceId}/report")
    public FileEntity getReport(@PathVariable("attendanceId") Long attendanceId){
        FileEntity fileEntity= attendanceService.getReport(attendanceId);
        if(fileEntity==null){
            return new FileEntity();
        }else{
            return fileEntity;
        }
    }

    /**
     * 获取ppt文件信息
     * @param attendanceId
     * @return
     */
    @GetMapping("/{attendanceId}/ppt")
    @RolesAllowed({"Teacher","Student"})
    public FileEntity getPpt(@PathVariable("attendanceId") Long attendanceId){
        FileEntity fileEntity= attendanceService.getPpt(attendanceId);
        if(fileEntity==null){
            return new FileEntity();
        }else{
            return fileEntity;
        }
    }

    /**
     * 获取报名讨论课小组列表
     * @param seminarKlassId
     * @return
     */
    @GetMapping("/{seminarKlassId}")
    @RolesAllowed({"Student","Teacher"})
    public ArrayList<AttendanceMessageVO> getBySeminarKlassId(@PathVariable("seminarKlassId")Long seminarKlassId){
        System.out.println("seminarKlassId为："+seminarKlassId);
        ArrayList<AttendanceMessageVO> attendanceMessageVOS=new ArrayList<>();
        ArrayList<AttendanceEntity> attendanceEntities=attendanceService.getBySeminarKlassId(seminarKlassId);
        for(int i=0;i<attendanceEntities.size();i++){
            attendanceEntities.get(i).setTeamEntity(teamService.getTeamMessageByTeamId(attendanceEntities.get(i).getTeamId()));
            AttendanceMessageVO attendanceMessageVO=new AttendanceMessageVO(attendanceEntities.get(i));
            SeminarScoreEntity seminarScoreEntity=seminarService.getOnceSeminarScore(seminarKlassId,attendanceEntities.get(i).getTeamId());
            try{
                attendanceMessageVO.setReportScore(seminarScoreEntity.getReportScore());
            }catch (NullPointerException e){
                attendanceMessageVO.setReportScore(null);
            }
            try{
                attendanceMessageVO.setScore(seminarScoreEntity.getPresentationScore());
            }catch (NullPointerException e){
                attendanceMessageVO.setScore(null);
            }
            attendanceMessageVOS.add(attendanceMessageVO);
        }
        return attendanceMessageVOS;
    }

    /**ljy
     * 报名展示
     * @param attendanceEntity
     * @return
     */
    @PostMapping("")
    @RolesAllowed("Student")
    public Long newAttendance(@RequestBody AttendanceEntity attendanceEntity,HttpServletResponse httpServletResponse){
        try {
            return attendanceService.newAttendance(attendanceEntity);
        } catch (SQLException e) {
            httpServletResponse.setStatus(400,e.getMessage());
        }
        return null;
    }


}
