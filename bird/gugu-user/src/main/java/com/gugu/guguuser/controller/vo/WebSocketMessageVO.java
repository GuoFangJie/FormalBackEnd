package com.gugu.guguuser.controller.vo;

/**
 * @author ren
 */
public class WebSocketMessageVO {
    Byte messageType;
    Long attendanceId;

    public Byte getMessageType() {
        return messageType;
    }

    public void setMessageType(Byte messageType) {
        this.messageType = messageType;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }
}
