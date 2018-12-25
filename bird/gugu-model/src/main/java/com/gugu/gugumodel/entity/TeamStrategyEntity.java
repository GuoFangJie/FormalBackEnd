package com.gugu.gugumodel.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用于存储组队策略信息
 * @author ljy
 */
public class TeamStrategyEntity{
    Long courseId;
    int minTeamMember;
    int maxTeamMember;
    //与策略集合
    ArrayList<Long> andStrategyIds;
    //或策略集合
    ArrayList<Long> orStrategyIds;


    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public int getMinTeamMember() {
        return minTeamMember;
    }

    public void setMinTeamMember(int minTeamMember) {
        this.minTeamMember = minTeamMember;
    }

    public int getMaxTeamMember() {
        return maxTeamMember;
    }

    public void setMaxTeamMember(int maxTeamMember) {
        this.maxTeamMember = maxTeamMember;
    }

    public ArrayList<Long> getAndStrategyIds() {
        return andStrategyIds;
    }

    public void setAndStrategyIds(ArrayList<Long> andStrategyIds) {
        this.andStrategyIds = andStrategyIds;
    }

    public ArrayList<Long> getOrStrategyIds() {
        return orStrategyIds;
    }

    public void setOrStrategyIds(ArrayList<Long> orStrategyIds) {
        this.orStrategyIds = orStrategyIds;
    }
}
