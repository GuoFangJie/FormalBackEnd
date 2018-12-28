package com.gugu.guguuser.controller;

import com.gugu.guguuser.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ren
 */
@RequestMapping("class")
@RestController
public class KlassController {
    @Autowired
    KlassService klassService;
    @Value("${loginPage}")
    String loginPage;
    /**
     * 导入学生名单
     * @param multipartFile
     * @param classId
     */

    @PostMapping("/{classId}")
    public void importStudentList(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, @RequestParam("fileUpload") MultipartFile multipartFile, @PathVariable("classId") Long classId) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        System.out.println(multipartFile.getOriginalFilename());
        klassService.importStudentList(multipartFile,classId);
    }


    /**
     * 按照id删除班级
     * @param klassId
     */
    @DeleteMapping("{classId}")
    @RolesAllowed("Teacher")
    public boolean deleteKlass(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@PathVariable("classId") Long klassId) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        return klassService.deleteClass(klassId);
    }
}
