package com.gugu.gugumodel.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用于储存管理员信息
 * @author TYJ
 */
public class TeamStrategyEntity{
    int minTeamMember;
    int maxTeamMember;

}
