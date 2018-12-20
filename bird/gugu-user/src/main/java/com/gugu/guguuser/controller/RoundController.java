package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.RoundEntity;
import com.gugu.gugumodel.entity.RoundScoreEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
import com.gugu.gugumodel.mapper.RoundScoreMapper;
import com.gugu.guguuser.controller.vo.EditRoundVO;
import com.gugu.guguuser.controller.vo.RoundScoreMessageVO;
import com.gugu.guguuser.service.RoundService;
import com.gugu.guguuser.service.SeminarService;
import com.gugu.guguuser.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Vector;

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
    @Autowired
    TeamService teamService;
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

    /**
     * 根据id修改轮次计分方式
     * @param editRoundVO
     * @param roundId
     */
    @PutMapping("/{roundId}")
    public void editRoundMessage(@RequestBody EditRoundVO editRoundVO, @PathVariable("roundId")Long roundId, HttpServletResponse httpServletResponse){
        RoundEntity roundEntity=new RoundEntity();
        roundEntity.setPresentationScoreMethod(editRoundVO.getCalculatePreType());
        roundEntity.setQuestionScoreMethod(editRoundVO.getCalculateQueType());
        roundEntity.setReportScoreMethod(editRoundVO.getCalculateRepType());
        roundEntity.setId(roundId);
        try {
            roundService.editRoundMessage(roundEntity);
        }catch (Exception e){
            httpServletResponse.setStatus(404,e.toString());
        }
    }

    /**
     * 新增round
     * @param roundEntity
     * @return
     */
    @PostMapping("")
    public Long newRound(@RequestBody RoundEntity roundEntity){
        return roundService.newRound(roundEntity);
    }

    /**
     * 获取一个轮次下所有小组的成绩 (还没写完）
     * @param roundId
     * @return
     */
    @GetMapping("{roundId}/roundscore")
    public Vector<RoundScoreMessageVO> getAllTeamScore(@PathVariable("roundId") Long roundId){
        ArrayList<RoundScoreEntity> roundScoreEntities=roundService.getAllTeamScore(roundId);
        Vector<RoundScoreMessageVO> roundScoreMessageVOS=new Vector<>();
        for(int i=0;i<roundScoreEntities.size();i++){
            RoundScoreMessageVO roundScoreMessageVO=new RoundScoreMessageVO();
            roundScoreMessageVO.setRoundScoreEntity(roundScoreEntities.get(i));
            roundScoreMessageVO.setTeamEntity(teamService.getTeamMessageByTeamId(roundScoreEntities.get(i).getTeamId()));
            roundScoreMessageVOS.add(roundScoreMessageVO);
        }
        return roundScoreMessageVOS;
    }
}
