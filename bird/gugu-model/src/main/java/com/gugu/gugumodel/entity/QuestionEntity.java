package com.gugu.gugumodel.entity;

/**
 * @author ren
 */
public class QuestionEntity {
    private Long id;
    private Long klassSeminarId;
    private Long attendanceId;
    private Long teamId;
    private Long studentId;
    private Byte isSelected;
    private Float score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Long klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Byte getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Byte isSelected) {
        this.isSelected = isSelected;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }


}
