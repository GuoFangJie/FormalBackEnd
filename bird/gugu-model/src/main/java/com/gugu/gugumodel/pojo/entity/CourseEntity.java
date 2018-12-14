package com.gugu.gugumodel.pojo.entity;

import java.sql.Timestamp;

/**
 * 用于储存比较完善的课程信息
 *目前用于新建课程
 * @author ren
 */
public class CourseEntity {
    Long id;
    Long teacher_id;
    String course_name;
    String introduction;
    int presentation_percentage;
    int question_percentage;
    int report_percentage;
    Timestamp  team_start_time;
    Timestamp team_end_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getPresentation_percentage() {
        return presentation_percentage;
    }

    public void setPresentation_percentage(int presentation_percentage) {
        this.presentation_percentage = presentation_percentage;
    }

    public int getQuestion_percentage() {
        return question_percentage;
    }

    public void setQuestion_percentage(int question_percentage) {
        this.question_percentage = question_percentage;
    }

    public int getReport_percentage() {
        return report_percentage;
    }

    public void setReport_percentage(int report_percentage) {
        this.report_percentage = report_percentage;
    }

    public Timestamp getTeam_start_time() {
        return team_start_time;
    }

    public void setTeam_start_time(Timestamp team_start_time) {
        this.team_start_time = team_start_time;
    }

    public Timestamp getTeam_end_time() {
        return team_end_time;
    }

    public void setTeam_end_time(Timestamp team_end_time) {
        this.team_end_time = team_end_time;
    }
}
