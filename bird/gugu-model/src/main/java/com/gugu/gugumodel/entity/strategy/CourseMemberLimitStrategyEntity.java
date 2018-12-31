package com.gugu.gugumodel.entity.strategy;

import com.gugu.gugumodel.entity.CourseEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public class CourseMemberLimitStrategyEntity{
    private Long id;
    private Long courseId;
    private Byte minMember;
    private Byte maxMember;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getMinMember() {
        return minMember;
    }

    public void setMinMember(Byte minMember) {
        this.minMember = minMember;
    }

    public Byte getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Byte maxMember) {
        this.maxMember = maxMember;
    }

}
