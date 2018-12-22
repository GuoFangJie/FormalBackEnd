package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.FileEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.guguuser.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

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

    /**
     * 修改展示顺序
     * @param httpServletResponse
     * @param attendance
     * @param teamOrder
     */
    @PutMapping("/{attendanceId}")
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
    @GetMapping("/{attendanceId}/report")
    public FileEntity getReport(@PathVariable("attendanceId") Long attendanceId){
        return attendanceService.getReport(attendanceId);
    }

    /**
     * 获取ppt文件信息
     * @param attendanceId
     * @return
     */
    @GetMapping("/{attendanceId}/ppt")
    public FileEntity getPpt(@PathVariable("attendanceId") Long attendanceId){
        return attendanceService.getPpt(attendanceId);
    }
}
