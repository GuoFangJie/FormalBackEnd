package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.AttendanceEntity;

/**
 * @author ren
 */
public class AttendanceMessageVO extends AttendanceEntity {
    Float score;
    Float reportScore;

    public Float getReportScore() {
        return reportScore;
    }

    public void setReportScore(Float reportScore) {
        this.reportScore = reportScore;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
    public AttendanceMessageVO(AttendanceEntity attendanceEntity){
        this.setTeamOrder(attendanceEntity.getTeamOrder());
        this.setTeamId(attendanceEntity.getTeamId());
        this.setReportUrl(attendanceEntity.getReportUrl());
        this.setReportName(attendanceEntity.getReportName());
        this.setPptUrl(attendanceEntity.getPptUrl());
        this.setPptName(attendanceEntity.getPptName());
        this.setKlassSeminarId(attendanceEntity.getKlassSeminarId());
        this.setIsPresent(attendanceEntity.getIsPresent());
        this.setId(attendanceEntity.getId());
        this.setTeamEntity(attendanceEntity.getTeamEntity());
    }
}
