package com.gugu.guguuser.controller;

import com.gugu.gugumodel.pojo.entity.ShareApplicationEntity;
import com.gugu.guguuser.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author TYJ
 */
@RestController
@RequestMapping("share")
public class ShareController {

    @Autowired
    ShareService shareService;

    /**
     * 获得共享讨论课信息
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/seminarshare")
    public ArrayList<ShareApplicationEntity> getSeminarShareList(HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return shareService.getSeminarShareList(userId);
    }
}
