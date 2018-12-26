package com.gugu.gugumodel.entity.strategy;

import com.gugu.gugumodel.entity.CourseEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public class CourseMemberLimitStrategyEntity implements Strategy {
    Long id;
    Long courseId;
    Byte minMember;
    Byte maxMember;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getMinMember() {
        return minMember;
    }

    public void setMinMember(Byte minMember) {
        this.minMember = minMember;
    }

    public Byte getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Byte maxMember) {
        this.maxMember = maxMember;
    }


    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        Integer count=0;
        for(ArrayList<CourseEntity> courseEntities:teamAllEntity.getStudentEntities()){
            for(CourseEntity courseEntity:courseEntities){
                if(courseEntity.getId().equals(this.courseId)){
                    count++;
                    break;
                }
            }
        }
        if(count>minMember&&count<maxMember){
            return false;
        }else{
            return true;
        }
    }
}
