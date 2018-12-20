package com.gugu.guguuser.controller;

import com.gugu.guguuser.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ren
 */
@RequestMapping("class")
@RestController
public class KlassController {
    @Autowired
    KlassService klassService;

    /**
     * 导入学生名单
     * @param multipartFile
     * @param classId
     */
    @PostMapping("/{classId}")
    public void importStudentList(@RequestParam("fileUpload") MultipartFile multipartFile, @PathVariable("classId") Long classId){
        System.out.println(multipartFile.getOriginalFilename());
        klassService.importStudentList(multipartFile,classId);
    }

    /**
     * 删除班级
     * @param klassId
     * @return
     */
    @DeleteMapping("{classId}")
    public boolean deleteKlass(@PathVariable("classId") Long klassId){
        return klassService.deleteClass(klassId);
    }
}
