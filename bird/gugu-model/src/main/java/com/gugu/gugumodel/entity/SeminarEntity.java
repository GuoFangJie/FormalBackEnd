package com.gugu.gugumodel.entity;

import java.sql.Timestamp;

/**
 * @author ren
 */
public class SeminarEntity {
    Long id;
    Long courseId;
    Long roundId;
    String seminarName;
    String introduction;
    Byte maxTeam;
    Byte isVisible;
    Byte seminarSerial;
    Timestamp enrollStartTime;
    Timestamp enrollEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Byte getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(Byte maxTeam) {
        this.maxTeam = maxTeam;
    }

    public Byte getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Byte isVisible) {
        this.isVisible = isVisible;
    }

    public Byte getSeminarSerial() {
        return seminarSerial;
    }

    public void setSeminarSerial(Byte seminarSerial) {
        this.seminarSerial = seminarSerial;
    }

    public Timestamp getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(Timestamp enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public Timestamp getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(Timestamp enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }
}
