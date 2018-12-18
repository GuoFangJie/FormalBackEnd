package com.gugu.gugumodel.pojo.entity;

/**
 * @author TYJ
 */
public class ShareApplicationEntity {
    private Long id;
    private CourseEntity mainCourseId;
    private CourseEntity subCourse;
    private TeacherEntity subCourseTeacher;
    private Byte status;
    private Byte type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseEntity getMainCourseId() {
        return mainCourseId;
    }

    public void setMainCourseId(CourseEntity mainCourseId) {
        this.mainCourseId = mainCourseId;
    }

    public CourseEntity getSubCourse() {
        return subCourse;
    }

    public void setSubCourse(CourseEntity subCourse) {
        this.subCourse = subCourse;
    }

    public TeacherEntity getSubCourseTeacher() {
        return subCourseTeacher;
    }

    public void setSubCourseTeacher(TeacherEntity subCourseTeacher) {
        this.subCourseTeacher = subCourseTeacher;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
