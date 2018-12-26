package com.gugu.gugumodel.entity.strategy;

/**
 * @author ren
 */
public class MemberLimitStrategy implements Strategy {

    Long id;
    Byte minMember;
    Byte maxMember;

    @Override
    public boolean isLegal(TeamAllEntity teamAllEntity) {
        return false;
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
}
