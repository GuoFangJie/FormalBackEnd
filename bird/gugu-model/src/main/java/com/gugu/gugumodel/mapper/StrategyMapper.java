package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.strategy.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
@Mapper
public interface StrategyMapper {
    /**
     * 获取team_strategy里面的数据
     * @param id
     * @return
     */
    ArrayList<TeamStrategyEntity> getTeamStrategy(Long id);

    /**
     * 获取team_and_strategy里面的数据
     * @param id
     * @return
     */
    ArrayList<TeamAndStrategyEntity> getTeamAndStrategy(Long id);

    /**
     * 获取team_or_strategy
     * @param id
     * @return
     */
    ArrayList<TeamOrStrategyEntity> getTeamOrStrategy(Long id);

    /**
     * 获取conflict_course_strategy里面的数据
     * @param id
     * @return
     */
    ArrayList<ConflictCourseStrategy> getConflictCourseStrategy(Long id);

    /**
     * 获取course_member_limit_strategy的数据
     * @param id
     * @return
     */
    CourseMemberLimitStrategyEntity getCourseMemberLimitStrategy(Long id);

    /**
     * 获取member_limit_strategy里面的数据
     * @param id
     * @return
     */
    MemberLimitStrategy getMemberLimitStrategy(Long id);
}
