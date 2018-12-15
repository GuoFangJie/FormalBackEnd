package com.gugu.gugumodel.pojo.vo;

import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;

import java.util.ArrayList;

/**
 * 将小组的详细信息返回给前端的类
 * @author ren
 */
public class TeamMessageVO {
    String team_name;
    Long course_id;
    Long klass_id;
    StudentEntity leader;
    ArrayList<StudentEntity> members;
    public TeamMessageVO(TeamEntity teamEntity,StudentEntity leader,ArrayList<StudentEntity> members){
        team_name=teamEntity.getTeam_name();
        course_id=teamEntity.getCourse_id();
        klass_id=teamEntity.getKlass_id();
        this.leader=leader;
        this.members=members;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getKlass_id() {
        return klass_id;
    }

    public void setKlass_id(Long klass_id) {
        this.klass_id = klass_id;
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
}
