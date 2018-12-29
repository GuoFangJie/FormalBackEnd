package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.QuestionEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.guguuser.controller.vo.QuestionVO;
import com.gugu.guguuser.service.QuestionService;
import com.gugu.guguuser.service.StudentService;
import com.gugu.guguuser.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @author ren
 */
@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeamService teamService;

    /**
     * 新建一个问题
     * @param attendanceId
     * @param httpServletRequest
     */
    @PostMapping("newQuestion")
    public void newQuestion(@RequestParam("attendanceId") Long attendanceId, HttpServletRequest httpServletRequest){
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        questionService.newQuestion(userId,attendanceId);
    }



    /**
     * 获取下一个问题
     * @param httpServletResponse
     * @param attendanceId
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/nextQuestion")
    public QuestionVO getNextQuestion(HttpServletResponse httpServletResponse,@RequestParam("attendanceId")Long attendanceId){
        QuestionVO questionVO=new QuestionVO();
        QuestionEntity questionEntity=questionService.getNext(attendanceId);
        if(questionEntity==null){
            httpServletResponse.setStatus(404,"暂时没有问题了");
        }else {
            questionVO.setQuestionEntity(questionEntity);
            questionVO.setStudentEntity(studentService.getStudentById(questionEntity.getStudentId()));
            questionVO.setTeamEntity(teamService.getTeamMessageByTeamId(questionEntity.getTeamId()));
            return questionVO;
        }
        return questionVO;
    }

    /**
     * 获取当前展示的问题列表
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/allquestion")
    public ArrayList<QuestionVO> getAllQuestion(HttpServletResponse httpServletResponse,@RequestParam("attendanceId")Long attendanceId){
        ArrayList<QuestionEntity> questionEntities=questionService.getAllQuestionByAttendanceId(attendanceId);
        ArrayList<QuestionVO> questionVOS=new ArrayList<>();
        for(QuestionEntity questionEntity:questionEntities){
            QuestionVO questionVO=new QuestionVO();
            questionVO.setQuestionEntity(questionEntity);
            questionVO.setStudentEntity(studentService.getStudentById(questionEntity.getStudentId()));
            questionVO.setTeamEntity(teamService.getTeamMessageByTeamId(questionEntity.getTeamId()));
            questionVOS.add(questionVO);
        }
        return questionVOS;
    }
}
