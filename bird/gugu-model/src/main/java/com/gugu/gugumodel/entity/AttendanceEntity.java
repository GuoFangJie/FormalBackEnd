package com.gugu.gugumodel.entity;

/**
 * @author ren
 */
public class AttendanceEntity {
    private Long id;
    private Long klassSeminarId;
    private Long teamId;
    private Byte teamOrder;
    private Byte isPresent;
    private String reportName;
    private String reportUrl;
    private String pptName;
    private String pptUrl;
    private TeamEntity teamEntity;

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Byte getTeamOrder() {
        return teamOrder;
    }

    public void setTeamOrder(Byte teamOrder) {
        this.teamOrder = teamOrder;
    }

    public Byte getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(Byte isPresent) {
        this.isPresent = isPresent;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportUrl() {
        return reportUrl;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getPptName() {
        return pptName;
    }

    public void setPptName(String pptName) {
        this.pptName = pptName;
    }

    public String getPptUrl() {
        return pptUrl;
    }

    public void setPptUrl(String pptUrl) {
        this.pptUrl = pptUrl;
    }
}
