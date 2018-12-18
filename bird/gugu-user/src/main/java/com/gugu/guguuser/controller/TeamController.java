package com.gugu.guguuser.controller;


import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import com.gugu.gugumodel.pojo.vo.TeamMessageVO;
import com.gugu.guguuser.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    /**
     * 新建队伍(还没有写完呢)
     * @param teamMessageVO
     * @return
     */
    @PostMapping("/")
    public void newTeam(@RequestBody TeamMessageVO teamMessageVO){
        StudentEntity leaderStuden=teamMessageVO.getLeader();
        ArrayList<StudentEntity> memberStudents=teamMessageVO.getMembers();
        TeamEntity teamEntity=new TeamEntity();
        teamEntity.setTeamName(teamMessageVO.getTeam_name());
        teamEntity.setCourseId(teamMessageVO.getCourse_id());
        teamEntity.setKlassId(teamMessageVO.getKlass_id());

    }

    /**
     * 获取当前用户的队伍信息
     * @param
     * @return
     */
//    @GetMapping("/")
//    public ArrayList<TeamMessageVO> getTeamMessageByStudentId(HttpServletRequest httpServletRequest){
//        Long studentId=Long.parseLong(httpServletRequest.getAttribute("student_id").toString());
//
//    }

    /**
     * 按照id获取队伍信息
     * @param teamId
     * @return
     */
    @GetMapping("/{teamId}")
    public TeamMessageVO getTeamById(@PathVariable Long teamId){
            TeamEntity teamEntity=teamService.getTeamMessageByTeamId(teamId);  //小组信息
            StudentEntity studentEntity=teamService.getLeaderByTeamId(teamId);  //组长信息
            ArrayList<StudentEntity> members=teamService.getMemberById(teamId); //组员信息
            TeamMessageVO teamMessageVO=new TeamMessageVO(teamEntity,studentEntity,members);
            return teamMessageVO;
    }
}
