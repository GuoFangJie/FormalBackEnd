package com.gugu.gugumodel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ren
 */
@Repository
@Mapper
public interface RoundScoreMapper {
    void deleteByTeamId(Long teamId);
}
