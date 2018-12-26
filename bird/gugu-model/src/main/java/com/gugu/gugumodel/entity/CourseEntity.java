package com.gugu.gugumodel.entity;

import com.gugu.gugumodel.entity.strategy.CourseMemberLimitStrategyEntity;
import com.gugu.gugumodel.entity.strategy.MemberLimitStrategy;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * 用于储存比较完善的课程信息
 * 目前用于新建课程
 * @author ren
 */
public class CourseEntity {
    private Long id;
    private Long teacherId;
    private String courseName;
    private String introduction;
    private Integer presentationPercentage;
    private Integer questionPercentage;
    private Integer reportPercentage;
    private Timestamp teamStartTime;
    private Timestamp teamEndTime;
    private MemberLimitStrategy memberLimitStrategy;
    private ArrayList<CourseMemberLimitStrategyEntity> courseMemberLimitStrategyEntityList;
    private boolean isAnd;
    private ArrayList<CourseEntity> conflictCourseList;
    private Long strategyId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getPresentationPercentage() {
        return presentationPercentage;
    }

    public void setPresentationPercentage(int presentationPercentage) {
        this.presentationPercentage = presentationPercentage;
    }

    public int getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(int questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public int getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(int reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public Timestamp getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(Timestamp teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public Timestamp getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(Timestamp teamEndTime) {
        this.teamEndTime = teamEndTime;
    }

    public MemberLimitStrategy getMemberLimitStrategy() {
        return memberLimitStrategy;
    }

    public void setMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy) {
        this.memberLimitStrategy = memberLimitStrategy;
    }

    public ArrayList<CourseMemberLimitStrategyEntity> getCourseMemberLimitStrategyEntityList() {
        return courseMemberLimitStrategyEntityList;
    }

    public void setCourseMemberLimitStrategyEntityList(ArrayList<CourseMemberLimitStrategyEntity> courseMemberLimitStrategyEntityList) {
        this.courseMemberLimitStrategyEntityList = courseMemberLimitStrategyEntityList;
    }

    public boolean isAnd() {
        return isAnd;
    }

    public void setAnd(boolean and) {
        isAnd = and;
    }

    public ArrayList<CourseEntity> getConflictCourseList() {
        return conflictCourseList;
    }

    public void setConflictCourseList(ArrayList<CourseEntity> conflictCourseList) {
        this.conflictCourseList = conflictCourseList;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
