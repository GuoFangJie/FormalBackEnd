package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class TeamStrategyEntity implements Strategy {
    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        return false;
    }
    Long courseId;
    Byte strategySerial;
    String strategyName;
    Long strategyId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Byte getStrategySerial() {
        return strategySerial;
    }

    public void setStrategySerial(Byte strategySerial) {
        this.strategySerial = strategySerial;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
