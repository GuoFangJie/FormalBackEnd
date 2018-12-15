package com.gugu.gugumodel.pojo.entity;

/**
 * 储存队伍信息的entity，与表对应
 * @author ren
 */
public class TeamEntity {
    Long id;
    Long klass_id;
    Long course_id;
    Long leader_id;
    String team_name;
    Integer team_serial;
    Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlass_id() {
        return klass_id;
    }

    public void setKlass_id(Long klass_id) {
        this.klass_id = klass_id;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Long leader_id) {
        this.leader_id = leader_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Integer getTeam_serial() {
        return team_serial;
    }

    public void setTeam_serial(Integer team_serial) {
        this.team_serial = team_serial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
