package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.strategy.CourseMemberLimitStrategyEntity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author ren
 */
public class NewCourseVO {
    private Long teacherId;
    private String courseName;
    private String introduction;
    private Integer presentationPercentage;
    private Integer questionPercentage;
    private Integer reportPercentage;
    private String teamStartTime;
    private String teamEndTime;
    private Byte maxMember;
    private Byte minMember;
    private ArrayList<CourseMemberLimitStrategyEntity> courseMemberLimitStrategyList;
    private boolean isAnd;
    private ArrayList<ArrayList<CourseEntity>> conflictCourseList;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPresentationPercentage() {
        return presentationPercentage;
    }

    public void setPresentationPercentage(Integer presentationPercentage) {
        this.presentationPercentage = presentationPercentage;
    }

    public Integer getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(Integer questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public Integer getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(Integer reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public String getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(String teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public String getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(String teamEndTime) {
        this.teamEndTime = teamEndTime;
    }

    public Byte getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Byte maxMember) {
        this.maxMember = maxMember;
    }

    public Byte getMinMember() {
        return minMember;
    }

    public void setMinMember(Byte minMember) {
        this.minMember = minMember;
    }

    public ArrayList<CourseMemberLimitStrategyEntity> getCourseMemberLimitStrategyList() {
        return courseMemberLimitStrategyList;
    }

    public void setCourseMemberLimitStrategyList(ArrayList<CourseMemberLimitStrategyEntity> courseMemberLimitStrategyList) {
        this.courseMemberLimitStrategyList = courseMemberLimitStrategyList;
    }

    public boolean isAnd() {
        return isAnd;
    }

    public void setAnd(boolean and) {
        isAnd = and;
    }

    public ArrayList<ArrayList<CourseEntity>> getConflictCourseList() {
        return conflictCourseList;
    }

    public void setConflictCourseList(ArrayList<ArrayList<CourseEntity>> conflictCourseList) {
        this.conflictCourseList = conflictCourseList;
    }
}
