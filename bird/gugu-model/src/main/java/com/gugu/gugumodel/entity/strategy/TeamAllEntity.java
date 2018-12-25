package com.gugu.gugumodel.entity.strategy;

import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.StudentEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public class TeamAllEntity {
    Integer numOfMember;
    ArrayList<ArrayList<CourseEntity>> studentEntities;

    public Integer getNumOfMember() {
        return numOfMember;
    }

    public void setNumOfMember(Integer numOfMember) {
        this.numOfMember = numOfMember;
    }

    public ArrayList<ArrayList<CourseEntity>> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(ArrayList<ArrayList<CourseEntity>> studentEntities) {
        this.studentEntities = studentEntities;
    }
}
