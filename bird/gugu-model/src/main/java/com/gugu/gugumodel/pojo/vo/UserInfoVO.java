package com.gugu.gugumodel.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;

/**
 * @author ren
 */
public class UserInfoVO {
    private Long id;
    private Byte isActive;
    private String userName;
    private String account;
    private String email;
    private String password;
    public UserInfoVO(TeacherEntity teacherEntity){
        this.id=teacherEntity.getId();
        this.isActive=teacherEntity.getIsActive();
        this.userName=teacherEntity.getTeacherName();
        this.account=teacherEntity.getAccount();
        this.email=teacherEntity.getEmail();
    }

    public UserInfoVO(StudentEntity studentEntity){
        this.id=studentEntity.getId();
        this.isActive=studentEntity.getIsActive();
        this.userName=studentEntity.getStudentName();
        this.account=studentEntity.getAccount();
        this.email=studentEntity.getEmail();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
