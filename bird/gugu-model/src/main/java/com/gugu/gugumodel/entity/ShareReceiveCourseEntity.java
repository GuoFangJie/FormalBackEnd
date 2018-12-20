package com.gugu.gugumodel.entity;

/**
 * @author ren
 * 储存在共享关系中作为接收方的课程信息和共享关系id
 */
public class ShareReceiveCourseEntity extends SimpleCourseEntity{
    Long shareId;

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }
}
