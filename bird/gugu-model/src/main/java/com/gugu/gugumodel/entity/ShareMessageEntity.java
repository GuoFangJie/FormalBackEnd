package com.gugu.gugumodel.entity;

/**
 * @author ren
 * 储存课程共享的信息
 */
public class ShareMessageEntity {
    Long shareId;
    /**
     * 1 为共享分组 2为共享讨论课
     */
    Integer shareType;
    SimpleCourseEntity masterCourse;
    SimpleCourseEntity recieveCourse;

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
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

    public SimpleCourseEntity getRecieveCourse() {
        return recieveCourse;
    }

    public void setRecieveCourse(SimpleCourseEntity recieveCourse) {
        this.recieveCourse = recieveCourse;
    }

    public ShareMessageEntity(SimpleCourseEntity mainCourse, ShareReceiveCourseEntity recieveCourse, Integer shareType){
        this.masterCourse=mainCourse;
        this.recieveCourse=(SimpleCourseEntity)recieveCourse;
        this.shareType=shareType;
        this.shareId=recieveCourse.getShareId();
    }



}
