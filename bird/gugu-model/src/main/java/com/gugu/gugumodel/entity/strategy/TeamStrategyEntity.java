package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class TeamStrategyEntity{
    private Long courseId;
    private Byte strategySerial;
    private String strategyName;
    private Long strategyId;

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
