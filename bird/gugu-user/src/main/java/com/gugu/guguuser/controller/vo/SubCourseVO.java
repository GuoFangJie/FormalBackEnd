package com.gugu.guguuser.controller.vo;

import java.util.ArrayList;

/**
 * @author ren
 */
public class SubCourseVO {
    ArrayList<Long> subCourseId;
    Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ArrayList<Long> getSubCourseId() {
        return subCourseId;
    }

    public void setSubCourseId(ArrayList<Long> subCourseId) {
        this.subCourseId = subCourseId;
    }
}
