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
    public SimpleCourseVO(SimpleCourseEntity simpleCourseEntity){
        this.setCourseName(simpleCourseEntity.getCourseName());
        this.setTeacherId(simpleCourseEntity.getTeacherId());
        this.setIntroduction(simpleCourseEntity.getIntroduction());
        this.setId(simpleCourseEntity.getId());
    }
}
