package com.gugu.guguuser.controller.vo;

import com.gugu.gugumodel.entity.SimpleCourseEntity;

/**
 * @author ren
 */
public class SimpleCourseVO extends SimpleCourseEntity {
    Long klassId;

    public Long getKlassId() {
        return klassId;
    }

    public void setKlassId(Long klassId) {
        this.klassId = klassId;
    }
}
