package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.QuestionEntity;
import com.gugu.gugumodel.exception.NotFoundException;
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
     * 给提问打分
     * @param httpServletResponse
     * @param questionId
     * @param score
     */
    @PutMapping("/{questionId}/score")
    public void scoreQuestion(HttpServletResponse httpServletResponse, @PathVariable("questionId")Long questionId, @RequestParam("score") Float score){
        try {
            questionService.scoreQuestion(questionId,score);
        } catch (NotFoundException e) {
            httpServletResponse.setStatus(404,e.getErrorMsg());
        }
    }
}
