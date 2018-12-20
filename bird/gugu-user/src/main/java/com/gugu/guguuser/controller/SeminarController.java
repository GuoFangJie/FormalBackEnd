package com.gugu.guguuser.controller;

import com.gugu.gugumodel.pojo.entity.*;
import com.gugu.guguuser.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/seminar")
public class SeminarController {

    @Autowired
    SeminarService seminarService;

    /**@author ljy
     * 新建讨论课,创建成功后返回seminarId
     * @return Long
     */
    @PostMapping("/")
    public Long newSeminar(@RequestBody SeminarEntity seminarEntity)throws ParseException {
        //DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        //seminarEntity.setEnrollSTime(formatter.parse(seminarEntity.getS()));
        //seminarEntity.setEnrollETime(formatter.parse(seminarEntity.getE()));
        return seminarService.newSeminar(seminarEntity);
    }

    /**@author ljy
     * 获取讨论课所属的班级信息
     * @param seminarId
     * @return KlassEntiry
     */
    @GetMapping("/{seminarId}/class")
    public KlassEntity getKlassSeminatIn(@PathVariable Long seminarId){
            return seminarService.getKlassSeminatIn(seminarId);
    }

    /**@author ljy
     * 按照id修改讨论课
     * @param seminarId
     * @return
     */
    @PutMapping("/{seminarId}")
    public boolean updateSeminar(@PathVariable Long seminarId,@RequestBody SeminarEntity seminarEntity){
        seminarEntity.setId(seminarId);
        return seminarService.updateSeminar(seminarEntity);
    }


    /**@author ljy
     * 按照id删除讨论课
     * @param seminarId
     * @return
     */
    @DeleteMapping("/{seminarId}")
    public boolean deleteSeminar(@PathVariable Long seminarId){
        return seminarService.deleteSeminar(seminarId);
    }
}
