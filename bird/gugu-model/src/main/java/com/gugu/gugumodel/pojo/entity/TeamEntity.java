package com.gugu.gugumodel.pojo.entity;

/**
 * 储存队伍信息的entity，与表对应
 * @author ren
 */
public class TeamEntity {
    Long id;
    Long klassId;
    Long courseId;
    Long leaderId;
    String teamName;
    Integer teamSerial;
    Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
