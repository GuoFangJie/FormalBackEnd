package com.gugu.guguuser.controller;


import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeamEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import com.gugu.guguuser.controller.vo.TeamMessageVO;
import com.gugu.guguuser.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    /**@author ljy
     * 新建队伍,返回队伍id
     * @param teamMessageVO
     * @return
     */
    @PostMapping("")
    @RolesAllowed("Student")
    public Long newTeam(@RequestBody TeamMessageVO teamMessageVO){
       // StudentEntity leaderStudent=teamMessageVO.getLeader();
        ArrayList<StudentEntity> memberStudents=teamMessageVO.getMembers();
        TeamEntity teamEntity=new TeamEntity();
       // teamEntity.setId(teamMessageVO.getTeamId());
        teamEntity.setKlassId(teamMessageVO.getKlassId());
        teamEntity.setCourseId(teamMessageVO.getCourseId());
        teamEntity.setLeaderId(teamMessageVO.getLeader().getId());
        teamEntity.setTeamName(teamMessageVO.getTeamName());
        teamEntity.setTeamSerial(teamMessageVO.getSerial());
        teamEntity.setStatus(teamMessageVO.getStatus());
        return teamService.newTeam(memberStudents,teamEntity);
    }

    /**@author ljy
     * 按照id获取队伍信息
     * @param teamId
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/{teamId}")
    public TeamMessageVO getTeamById(@PathVariable Long teamId){
        if(teamId==null){
            return null;
        }
        //小组信息
        TeamEntity teamEntity=teamService.getTeamMessageByTeamId(teamId);
        if(teamEntity==null){
            return null;
        }
        //组长信息
        StudentEntity studentEntity=teamService.getLeaderByTeamId(teamId);
        //组员信息
        ArrayList<StudentEntity> members=teamService.getMemberById(teamId);
        TeamMessageVO teamMessageVO=new TeamMessageVO(teamEntity,studentEntity,members);
        return teamMessageVO;
    }

    /**@author ljy
     * 更新小组信息
     * @param teamMessageVO
     * @param teamId
     */
    @RolesAllowed("Student")
    @PutMapping("/{teamId}")
    public void updateTeam(@PathVariable Long teamId,@RequestBody TeamMessageVO teamMessageVO){
        teamMessageVO.setTeamId(teamId);
        TeamEntity teamEntity=new TeamEntity();
        teamEntity.setKlassId(teamMessageVO.getKlassId());
        teamEntity.setCourseId(teamMessageVO.getCourseId());
        teamEntity.setTeamName(teamMessageVO.getTeamName());
        teamEntity.setLeaderId(teamMessageVO.getLeader().getId());
        teamEntity.setStatus(teamMessageVO.getStatus());
        teamEntity.setId(teamMessageVO.getTeamId());
        ArrayList<StudentEntity> memberStudents=teamMessageVO.getMembers();
        teamService.updateTeam(teamEntity,memberStudents);
    }

    /**@author ljy
     * 按照ID删除队伍或者组长解散队伍
     * @param teamId
     */

    @Secured("ROLE_Student")
    @DeleteMapping("/{teamId}")
    public void deleteTeam(@PathVariable Long teamId){
        teamService.deleteTeam(teamId);
    }

    /**@author ljy
     * 添加成员
     * @param teamId
     * @param studentEntity
     */
    @RolesAllowed("Student")
    @PutMapping("/{teamId}/add")
    public Byte addMember(@PathVariable Long teamId, @RequestBody StudentEntity studentEntity,Long courseId){
        Long studentId=studentEntity.getId();
        return teamService.addMember(teamId,studentId,courseId);
    }

    /**@author ljy
     * 移除成员和退出队伍
     * @param teamId
     * @param studentEntity
     */
    @RolesAllowed("Student")
    @PutMapping("/{teamId}/remove")
    public Byte removeMember(@PathVariable Long teamId,@RequestBody StudentEntity studentEntity,Long courseId){
        Long studentId=studentEntity.getId();
        return teamService.removeMember(teamId,studentId,courseId);
    }

    /**@author ljy
     * 发起队伍状态申请
     * @param teamId
     * @param teamValidEntity
     */
    @RolesAllowed("Student")
    @PostMapping("/{teamId}/teamvalidrequest")
    public void teamValidRequest(@PathVariable Long teamId, @RequestBody TeamValidEntity teamValidEntity){
        teamValidEntity.setTeamId(teamId);
        teamService.teamValidRequest(teamValidEntity);
        teamService.setTeamStatus(teamId);
    }

}
