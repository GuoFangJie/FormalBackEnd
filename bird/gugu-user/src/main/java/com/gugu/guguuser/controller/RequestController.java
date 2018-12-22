package com.gugu.guguuser.controller;

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
    TeamRequestService teamValidService;

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
     * 教师获得组队申请信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/teamvalid")
    public ArrayList<Map> getTeamRequestList(HttpServletRequest httpServletRequest){
        Long teacherId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return teamValidService.getTeamRequestList(teacherId);
    }
}
