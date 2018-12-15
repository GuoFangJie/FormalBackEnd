package com.gugu.gugumodel.pojo.entity;

/**
 * 用于根据用户id获取相关课程基本信息
 * @author ren
 */
public class SimpleCourseEntity {
    Integer id;
    Integer teacherId;
    String courseName;
    String introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
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
}
