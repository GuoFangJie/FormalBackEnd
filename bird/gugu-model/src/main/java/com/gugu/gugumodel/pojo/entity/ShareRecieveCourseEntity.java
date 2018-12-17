package com.gugu.gugumodel.pojo.entity;

/**
 * @author ren
 * 储存在共享关系中作为接收方的课程信息和共享关系id
 */
public class ShareRecieveCourseEntity extends SimpleCourseEntity{
    Long shareId;

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }
}
