package com.gugu.guguuser.controller;

import com.gugu.gugumodel.exception.ParamErrorException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gugu.gugumodel.entity.ShareApplicationEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import com.gugu.guguuser.service.ShareService;
import com.gugu.guguuser.service.TeamRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author TYJ
 */
@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    ShareService shareService;

    @Autowired
    TeamRequestService teamRequestService;

    /**
     * 获得共享讨论课信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/seminarshare")
    public ArrayList<Map> getSeminarShareList(HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return shareService.getSeminarShareList(userId);
    }

    /**
     * 修改共享讨论课申请的状态
     * @param requestId
     * @param handleType
     * @return
     */
    @PutMapping("/{requestId}/seminarshare")
    public boolean changeSeminarShareStatus(@PathVariable("requestId") Long requestId,@RequestBody String handleType) throws ParamErrorException {
        return shareService.changeSeminarShareStatus(requestId,handleType);
    }

    /**
     * 获得共享组队信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/teamshare")
    public ArrayList<Map> getTeamShareList(HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return shareService.getTeamShareList(userId);
    }

    /**
     * 修改共享分组申请的状态
     * @param requestId
     * @param handleType
     * @return
     */
    @PutMapping("/{requestId}/teamshare")
    public boolean changeTeamShareStatus(@PathVariable("requestId") Long requestId,@RequestBody String handleType) throws ParamErrorException {
        return shareService.changeTeamShareStatus(requestId,handleType);
    }

    /**
     * 教师获得组队申请信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/teamvalid")
    public ArrayList<Map> getTeamRequestList(HttpServletRequest httpServletRequest){
        Long teacherId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return teamRequestService.getTeamRequestList(teacherId);
    }

    /**
     * 修改共享小组申请的状态
     * @param requestId
     * @param handleType
     * @return
     */
    @PutMapping("/{requestId}/teamvalid")
    public boolean changeTeamRequestStatus(@PathVariable("requestId") Long requestId,@RequestBody String handleType) throws ParamErrorException {
        return teamRequestService.changeTeamRequestStatus(requestId,handleType);
    }
}
