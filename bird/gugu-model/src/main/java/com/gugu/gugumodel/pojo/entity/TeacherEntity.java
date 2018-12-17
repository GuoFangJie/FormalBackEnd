package com.gugu.gugumodel.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 储存教师信息的entity
 * 不存放账号密码
 * @author ljy
 */
public class TeacherEntity {
    private Long id;
    private Byte isActive;
    private String teacherName;
    private String account;
    private String email;
    @JsonIgnore
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String studentName) {
        this.teacherName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
