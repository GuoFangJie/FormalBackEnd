package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.SeminarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
@Mapper
public interface SeminarMapper {
    /**
     * 根据round获取讨论课信息列表
     * @param roundId
     * @return
     */
    ArrayList<SeminarEntity> getSeminarsByRound(Long roundId);
}
