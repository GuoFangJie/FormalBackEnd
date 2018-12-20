package com.gugu.gugumodel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 储存学生信息的entity，感觉老师和学生的表一样
 * 不存放账号密码
 * @author ren
 */
public class StudentEntity {
    private Long id;
    private Byte isActive;
    private String studentName;
    private String account;
    private String email;
    @JsonIgnore
    private String password;

    public StudentEntity(){
    }
    public StudentEntity(TeacherEntity teacherEntity){
        this.id=teacherEntity.getId();
        this.isActive=teacherEntity.getIsActive();
        this.studentName=teacherEntity.getTeacherName();
        this.account=teacherEntity.getAccount();
        this.email=teacherEntity.getEmail();
    }

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
