package com.gugu.guguuser.controller;


import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import com.gugu.gugumodel.pojo.vo.TeamMessageVO;
import com.gugu.guguuser.service.TeamService;
import com.gugu.guguuser.service.TeamService;
import org.apache.ibatis.annotations.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    /**@author ljy
     * 新建队伍(还没有写完呢,队伍是否合法是前端还是后端判断)
     * @param teamMessageVO
     * @return
     */
    @PostMapping("/")
    public void newTeam(@RequestBody TeamMessageVO teamMessageVO){
        StudentEntity leaderStudent=teamMessageVO.getLeader();
        ArrayList<StudentEntity> memberStudents=teamMessageVO.getMembers();
        TeamEntity teamEntity=new TeamEntity();
        teamEntity.setTeamName(teamMessageVO.getTeam_name());
        teamEntity.setCourseId(teamMessageVO.getCourse_id());
        teamEntity.setKlassId(teamMessageVO.getKlass_id());

    }

    /**@author ljy
     * 按照id获取队伍信息
     * @param teamId
     * @return
     */
    @GetMapping("/{teamId}")
    public TeamMessageVO getTeamById(@PathVariable Long teamId){
        //小组信息
        TeamEntity teamEntity=teamService.getTeamMessageByTeamId(teamId);
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
    @PutMapping("/{teamId}")
    public void updateTeam(@PathVariable Long teamId,@RequestBody TeamMessageVO teamMessageVO){
        teamMessageVO.setTeamId(teamId);
        TeamEntity teamEntity=new TeamEntity(teamMessageVO);
        ArrayList<StudentEntity> memberStudents=teamMessageVO.getMembers();
        teamService.updateTeam(teamEntity,memberStudents);
    }

    /**@author ljy
     * 按照ID删除队伍或者组长解散队伍
     * @param teamId
     */
    @DeleteMapping("/{teamId}")
    public void deleteTeam(@PathVariable Long teamId){
        teamService.deleteTeam(teamId);
    }

    /**@author ljy
     * 添加成员
     * @param teamId
     * @param studentEntity
     */
    @PutMapping("/{teamId}/add")
    public void addMember(@PathVariable Long teamId,@RequestBody StudentEntity studentEntity){
        Long studentId=studentEntity.getId();
        teamService.addMember(teamId,studentId);
    }

    /**@author ljy
     * 移除成员和退出队伍
     * @param teamId
     * @param studentEntity
     */
    @PutMapping("/{teamId}/remove")
    public void removeMember(@PathVariable Long teamId,@RequestBody StudentEntity studentEntity){
        Long studentId=studentEntity.getId();
        teamService.removeMember(teamId,studentId);
    }

    /**@author ljy
     * 发起队伍状态申请
     * @param teamId
     * @param studentEntity
     */
//    @PostMapping("/{teamId}/teamvaildrequest")
//    public
}
