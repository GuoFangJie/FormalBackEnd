package com.gugu.gugumodel.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ljy
 */
public class KlassSeminarEntity {
  SeminarEntity seminarEntity;
  KlassEntity klassEntity;
  Long klassId;
  Long klassSeminarId;
  Date reportDDL;
  Byte status;
    SeminarEntity seminarEntity;
    Long klassId;
    Long klassSeminarId;
    Date reportDDL;
    Byte status;

    public KlassEntity getKlassEntity() {
        return klassEntity;
    }

    public void setKlassEntity(KlassEntity klassEntity) {
        this.klassEntity = klassEntity;
    }

    public SeminarEntity getSeminarEntity() {
        return seminarEntity;
    }

    public void setSeminarEntity(SeminarEntity seminarEntity) {
        this.seminarEntity = seminarEntity;
    }

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }

    public Long getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(Long klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public Date getReportDDL() {
        return reportDDL;
    }

    public void setReportDDL(Date reportDDL) {
        this.reportDDL = reportDDL;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
