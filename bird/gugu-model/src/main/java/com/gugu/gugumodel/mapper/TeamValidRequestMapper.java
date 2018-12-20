package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.TeamValidEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ljy
 */
@Mapper
@Repository
public interface TeamValidRequestMapper {

     void teamValidRequest(TeamValidEntity teamValidEntity);

     Long getTeamValidStatus(Long teamId);
}
