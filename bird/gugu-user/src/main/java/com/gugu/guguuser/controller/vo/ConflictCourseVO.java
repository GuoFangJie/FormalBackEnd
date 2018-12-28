package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.CourseEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public class ConflictCourseVO {
    private ArrayList<CourseEntity> conflictList;

    public ArrayList<CourseEntity> getConflictList() {
        return conflictList;
    }

    public void setConflictList(ArrayList<CourseEntity> conflictList) {
        this.conflictList = conflictList;
    }
}
