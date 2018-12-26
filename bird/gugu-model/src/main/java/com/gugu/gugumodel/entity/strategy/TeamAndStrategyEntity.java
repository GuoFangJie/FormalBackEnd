package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class TeamAndStrategyEntity implements Strategy {
    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        return false;
    }
    Long id;
    String strategyName;
    Long strategyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
