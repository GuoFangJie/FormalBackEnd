package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeamEntity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 将小组的详细信息返回给前端的类
 * @author ren
 */
public class TeamMessageVO implements Serializable {
    Long teamId;
    String teamName;
    Long courseId;
    Long klassId;
    StudentEntity leader;
    ArrayList<StudentEntity> members;
    Integer status;
    Integer serial;


    public TeamMessageVO(){

    }


    public TeamMessageVO(TeamEntity teamEntity, StudentEntity leader, ArrayList<StudentEntity> members){
        teamName=teamEntity.getTeamName();
        courseId=teamEntity.getCourseId();
        klassId=teamEntity.getKlassId();
        this.leader=leader;
        this.members=members;
        this.status=teamEntity.getStatus();
        this.teamId=teamEntity.getId();
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public StudentEntity getLeader() {
        return leader;
    }

    public void setLeader(StudentEntity leader) {
        this.leader = leader;
    }

    public ArrayList<StudentEntity> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<StudentEntity> members) {
        this.members = members;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }
}
