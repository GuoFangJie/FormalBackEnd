package com.gugu.gugumodel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ren
 */
@Repository
@Mapper
public interface KlassRoundMapper {
    /**
     * 根据客户才能删除klassRound的记录
     * @param klassId
     */
    void deleteByKlass(Long klassId);

    /**
     * 新建round和班级关系
     * @param klassId
     * @param roundId
     */
    void newKlassRound(Long klassId,Long roundId);

    /**
     * 删除课程下所有klassRound
     * @param courseId
     */
    void deleteAllKlassRoundByCourseId(Long courseId);
}
