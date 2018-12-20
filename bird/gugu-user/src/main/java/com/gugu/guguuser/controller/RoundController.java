package com.gugu.guguuser.controller;

import com.gugu.gugumodel.pojo.entity.RoundEntity;
import com.gugu.gugumodel.pojo.entity.SeminarEntity;
import com.gugu.guguuser.service.RoundService;
import com.gugu.guguuser.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @author ren
 */
@RestController
@RequestMapping("round")
public class RoundController {
    @Autowired
    SeminarService seminarService;
    @Autowired
    RoundService roundService;
    /**
     * 获取round下所有的seminar
     * @param roundId
     * @return
     */
    @GetMapping("/{roundId}/seminar")
    public ArrayList<SeminarEntity> getSeminarByRound(@PathVariable("roundId") Long roundId){
        return seminarService.getSeminarByRound(roundId);
    }

    /**
     * 根据id获取详细信息
     * @param roundId
     * @return
     */
    @GetMapping("{roundId}")
    public RoundEntity getMessageById(@PathVariable("roundId")Long roundId){
        return roundService.getMessageById(roundId);
    }
}
