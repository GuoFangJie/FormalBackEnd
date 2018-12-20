package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.RoundEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ren
 */
@Repository
@Mapper
public interface RoundMapper {
    /**
     * 根据id获取round详细信息
     * @param roundId
     * @return
     */
    RoundEntity getRoundMessageById(Long roundId);
}
