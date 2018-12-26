package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.QuestionEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.guguuser.controller.vo.QuestionVO;
import com.gugu.guguuser.service.QuestionService;
import com.gugu.guguuser.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @GetMapping("/nextQuestion")
    public QuestionVO getNextQuestion(HttpServletResponse httpServletResponse,@RequestParam("attendanceId")Long attendanceId){
        QuestionVO questionVO=new QuestionVO();
        QuestionEntity questionEntity=questionService.getNext(attendanceId);
        if(questionEntity==null){
            httpServletResponse.setStatus(404,"暂时没有问题了");
        }else {
            questionVO.setQuestionEntity(questionEntity);
            questionVO.setStudentEntity(studentService.getStudentById(questionEntity.getStudentId()));
            return questionVO;
        }
        return questionVO;
    }
}
