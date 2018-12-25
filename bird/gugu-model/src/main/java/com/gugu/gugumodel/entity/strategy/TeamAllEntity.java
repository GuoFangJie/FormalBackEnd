package com.gugu.gugumodel.entity.strategy;

import com.gugu.gugumodel.entity.StudentEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public class TeamAllEntity {
    Integer numOfMember;
    ArrayList<StudentEntity> studentEntities;

    public Integer getNumOfMember() {
        return numOfMember;
    }

    public void setNumOfMember(Integer numOfMember) {
        this.numOfMember = numOfMember;
    }

    public ArrayList<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(ArrayList<StudentEntity> studentEntities) {
        this.studentEntities = studentEntities;
    }
}
