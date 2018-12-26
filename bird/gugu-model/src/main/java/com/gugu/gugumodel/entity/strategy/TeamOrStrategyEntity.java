package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class TeamOrStrategyEntity implements Strategy {
    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        return false;
    }
    Long id;
    String strategy;
    Long strategyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
