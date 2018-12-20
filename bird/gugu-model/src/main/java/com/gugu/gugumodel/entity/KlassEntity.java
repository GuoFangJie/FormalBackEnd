package com.gugu.gugumodel.entity;

/**
 * @author ren
 */
public class KlassEntity {
    private Long id;
    private Long courseId;
    private Integer grade;
    private Byte klassSerial;
    private String klassTime;
    private String klassLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Byte getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Byte klassSerial) {
        this.klassSerial = klassSerial;
    }

    public String getKlassTime() {
        return klassTime;
    }

    public void setKlassTime(String klassTime) {
        this.klassTime = klassTime;
    }

    public String getKlassLocation() {
        return klassLocation;
    }

    public void setKlassLocation(String klassLocation) {
        this.klassLocation = klassLocation;
    }
}
