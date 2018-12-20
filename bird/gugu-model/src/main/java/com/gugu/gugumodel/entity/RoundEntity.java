package com.gugu.gugumodel.entity;

/**
 * @author ren
 */
public class RoundEntity {
    Long id;
    Long courseId;
    Byte roundSerial;
    Byte presentationScoreMethod;
    Byte reportScoreMethod;
    Byte questionScoreMethod;

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

    public Byte getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Byte roundSerial) {
        this.roundSerial = roundSerial;
    }

    public Byte getPresentationScoreMethod() {
        return presentationScoreMethod;
    }

    public void setPresentationScoreMethod(Byte presentationScoreMethod) {
        this.presentationScoreMethod = presentationScoreMethod;
    }

    public Byte getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(Byte reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public Byte getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(Byte questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }
}
