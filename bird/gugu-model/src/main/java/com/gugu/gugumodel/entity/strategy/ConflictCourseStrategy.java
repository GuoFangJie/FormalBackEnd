package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class ConflictCourseStrategy implements Strategy {
    Long id;
    Long courseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        return false;
    }
}
