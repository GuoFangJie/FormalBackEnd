package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.SimpleCourseEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public class ShareMessageVO {
    /**
     * 1 为共享分组 2为共享讨论课
     */
    Integer shareType;
    SimpleCourseEntity masterCourse;
    ArrayList<SimpleCourseEntity> recieveCourse;

    public void addRecieveCourse(SimpleCourseEntity simpleCourseEntity){
        recieveCourse.add(simpleCourseEntity);
    }


    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public SimpleCourseEntity getMasterCourse() {
        return masterCourse;
    }

    public void setMasterCourse(SimpleCourseEntity masterCourse) {
        this.masterCourse = masterCourse;
    }

    public ArrayList<SimpleCourseEntity> getRecieveCourse() {
        return recieveCourse;
    }

    public void setRecieveCourse(ArrayList<SimpleCourseEntity> recieveCourse) {
        this.recieveCourse = recieveCourse;
    }
}
