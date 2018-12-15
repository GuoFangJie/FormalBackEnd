package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeamMapper {
    TeamEntity findTeamById(Long team_id);
}
