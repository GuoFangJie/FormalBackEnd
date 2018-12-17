package com.gugu.gugumodel.pojo.entity;

/**
 * @author ren
 */
public class ShareApplicationEntity {
    Long id;
    Long mainCourseId;
    Long subCourseId;
    Long subCourseTeacherId;
    Byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainCourseId() {
        return mainCourseId;
    }

    public void setMainCourseId(Long mainCourseId) {
        this.mainCourseId = mainCourseId;
    }

    public Long getSubCourseId() {
        return subCourseId;
    }

    public void setSubCourseId(Long subCourseId) {
        this.subCourseId = subCourseId;
    }

    public Long getSubCourseTeacherId() {
        return subCourseTeacherId;
    }

    public void setSubCourseTeacherId(Long subCourseTeacherId) {
        this.subCourseTeacherId = subCourseTeacherId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
