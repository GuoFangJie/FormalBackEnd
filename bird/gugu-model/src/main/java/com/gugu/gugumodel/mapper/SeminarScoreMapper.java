package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.SeminarScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Mapper
@Repository
public interface SeminarScoreMapper {
    ArrayList<SeminarScoreEntity> getTeamAllScore(Long team_id);
}
