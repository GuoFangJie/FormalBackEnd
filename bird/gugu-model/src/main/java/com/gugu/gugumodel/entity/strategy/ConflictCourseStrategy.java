package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class ConflictCourseStrategy implements Strategy {

    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        return false;
    }
}
