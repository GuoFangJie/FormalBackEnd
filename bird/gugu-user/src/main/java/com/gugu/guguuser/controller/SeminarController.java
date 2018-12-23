package com.gugu.guguuser.controller;

import com.gugu.gugumodel.dao.KlassDao;
import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.KlassSeminarEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.guguuser.service.KlassService;
import com.gugu.guguuser.service.RoundService;
import com.gugu.guguuser.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/seminar")
public class SeminarController {
    @Autowired
    SeminarService seminarService;
    @Autowired
    RoundService roundService;
    @Autowired
    KlassService klassService;

    /**@author ljy
     * 新建讨论课,创建成功后返回seminarId
     * @return Long
     */
    @PostMapping("")
    public Long newSeminar(@RequestBody SeminarEntity seminarEntity)throws ParseException {
        DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        seminarEntity.setEnrollSTime(formatter.parse(seminarEntity.getStart()));
        seminarEntity.setEnrollETime(formatter.parse(seminarEntity.getEnd()));
        if(seminarEntity.getRoundId()==-1){
            RoundEntity roundEntity=new RoundEntity();
            roundEntity.setCourseId(seminarEntity.getCourseId());
            roundEntity.setRoundSerial(seminarEntity.getRoundSerial());
            Byte t=1;
            roundEntity.setPresentationScoreMethod(t);
            roundEntity.setQuestionScoreMethod(t);
            roundEntity.setReportScoreMethod(t);
            seminarEntity.setRoundId(roundService.newRound(roundEntity));
           // System.out.println(roundEntity.getId());

        }
        return seminarService.newSeminar(seminarEntity);
    }

    /**@author ljy
     * 获取讨论课所属的班级信息
     * @param seminarId
     * @return KlassEntiry
     */
    @GetMapping("/{seminarId}/class")
    public ArrayList<KlassEntity> getKlassSeminatIn(@PathVariable Long seminarId){
        return seminarService.getKlassSeminatIn(seminarId);
    }

    /**@author ljy
     * 按照id修改讨论课
     * @param seminarId
     * @return
     */
    @PutMapping("/{seminarId}")
    public boolean updateSeminar(@PathVariable Long seminarId,@RequestBody SeminarEntity seminarEntity)throws ParseException{
        seminarEntity.setId(seminarId);
        DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
        seminarEntity.setEnrollSTime(formatter.parse(seminarEntity.getStart()));
        seminarEntity.setEnrollETime(formatter.parse(seminarEntity.getEnd()));
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

    /**@author ljy
     * 按照id获取讨论课
     * @param seminarId
     * @return
     */
    @GetMapping("/{seminarId}")
    public SeminarEntity getSeminarById(@PathVariable Long seminarId)throws NotFoundException{
        SeminarEntity seminarEntity=seminarService.getSeminarById(seminarId);
        if(seminarEntity==null)
        {
            throw new NotFoundException("can't find seminar!");
        }
        seminarEntity.setRoundSerial(roundService.getRoundSerialById(seminarEntity.getRoundId()));
        return seminarEntity;
    }

    /**@author ljy
     * 按照id修改班级讨论课（设置不同班级讨论课的报告提交时间）
     * @param seminarId
     * @return
     */
    @PutMapping("/{seminarId}/class/{classId}")
    public boolean setReportDDLInClass(@PathVariable("seminarId") Long seminarId,@PathVariable("classId")Long classId,@RequestBody String time)throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        Date date=formatter.parse(time);
        return seminarService.setReportDDLInClass(seminarId,classId,date);
    }

    /**@author ljy
     * 按照id删除班级讨论课
     * @param seminarId
     * @return
     */
    @DeleteMapping("/{seminarId}/class/{classId}")
    public boolean deleteSeminarInClass(@PathVariable("seminarId")Long seminarId,@PathVariable("classId")Long classId){
        return seminarService.deleteSeminarInClass(seminarId,classId);
    }

    /**@author ljy
     * 按照id获取班级下讨论课
     * @param seminarId
     * @return
     */
    @GetMapping("/{seminarId}/class/{classId}")
    public KlassSeminarEntity getSeminarInClass(@PathVariable("seminarId")Long seminarId, @PathVariable("classId")Long classId){
        //获取seminar中的信息和klass_seminar中的讨论课状态
         KlassSeminarEntity klassSeminarEntity=seminarService.getSeminarInClass(seminarId,classId);
         klassSeminarEntity.setKlassEntity(klassService.getKlassById(classId));
        return klassSeminarEntity;
    }

    /**@author ljy
     * 设置讨论课轮次
     * @param seminarId
     * @return
     */
    @PutMapping("/{seminarId}/round")
    public boolean setSeminarRound(@PathVariable Long seminarId, @RequestBody RoundEntity roundEntity){
        return seminarService.setSeminarRound(seminarId,roundEntity);
    }

    /**@author ljy
     * 设置讨论课状态
     * @param seminarId
     * @return
     */
    @PutMapping("/{seminarId}/class/{classId}/status")
    public boolean setSeminarStatus(@PathVariable("seminarId") Long seminarId,@PathVariable("classId") Long classId,@RequestBody KlassSeminarEntity klassSeminarEntity){
        Byte status=klassSeminarEntity.getStatus();
        return seminarService.setSeminarStatus(seminarId,classId,status);
    }

    /**@author ljy
     * 设置班级下讨论课书面报告截止时间
     * @param seminarId
     * @return
     */
    @PutMapping("/{seminarId}/class/{classId}/reportddl")
    public boolean setSeminarReportddl(@PathVariable("seminarId") Long seminarId,@PathVariable("classId") Long classId,@RequestParam("date") String d)throws ParseException{
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(d);
        return seminarService.setSeminarReportddl(seminarId,classId,date);
    }

    /**@author ljy
     * 获取班级下小组在一次讨论课下的成绩
     * @param seminarId
     * @return
     */
    @GetMapping("/{seminarId}/team/{teamId}/senimarscore")
    public SeminarScoreEntity getSeminarScore(@PathVariable("seminarId") Long seminarId,@PathVariable("teamId")Long teamId){
        return seminarService.getSeminarScore(seminarId,teamId);
    }

    /**@author ljy
     * 按照seminarid修改队伍讨论课成绩
     * @param seminarId
     * @return
     */
    @PutMapping("/{seminarId}/team/{teamId}/senimarscore")
    public boolean setSeminarScore(@PathVariable("seminarId") Long seminarId,@PathVariable("teamId")Long teamId,@RequestBody SeminarScoreEntity seminarScoreEntity){
        seminarScoreEntity.setTeamId(teamId);
        return seminarService.setSeminarScore(seminarId,teamId,seminarScoreEntity);
    }

    /**@author ljy
     * 按照seminarid获取讨论课所有小组成绩
     * @param seminarId
     * @return
     */
    @GetMapping("/{seminarId}/seminarscore")
    public ArrayList<SeminarScoreEntity> getSeminarAllScore(@PathVariable Long seminarId,Long classId){
        return seminarService.getSeminarAllScore(seminarId,classId);
    }

    /**
     * 连接websocket
     * @param seminarKlassId
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/{seminarKlassId}/seminarEnter")
    public String  enterSeminar(@PathVariable("seminarKlassId")Long seminarKlassId, HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        String role=httpServletRequest.getAttribute("role").toString();
        return "ws://jknwqf.natappfree.cc/websocket/"+seminarKlassId+"/"+userId+"/"+role+"";
    }
}
