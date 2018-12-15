package com.gugu.gugumodel.pojo.entity;

/**
 * 储存学生信息的entity，感觉老师和学生的表一样
 * 不存放账号密码
 * @author ren
 */
public class StudentEntity {
    Long id;
    Integer is_active;
    String student_name;
    String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}