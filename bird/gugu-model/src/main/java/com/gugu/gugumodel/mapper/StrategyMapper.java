package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.strategy.CourseMemberLimitStrategyEntity;
import com.gugu.gugumodel.entity.strategy.MemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Mapper
@Repository
public interface StrategyMapper {

     /**
      * 加入课程自身规则
      * @param memberLimitStrategy
      */
     Long addMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy);

     /**
      * 加入课程自身规则
      * @param courseMemberLimitStrategyEntity
      */
     Long addCourseMemberLimitStrategy(CourseMemberLimitStrategyEntity courseMemberLimitStrategyEntity);

     /**
      * 加入“与”表
      * @param id
      * @param strategyId
      * @param strategyName
      */
     Long andCourseMemberLimitStrategy(Long id,Long strategyId,String strategyName);

     /**
      * 加入“或”表
      * @param id
      * @param strategyId
      * @param strategyName
      */
     Long orCourseMemberLimitStrategy(Long id,Long strategyId,String strategyName);

     /**
      * 获取与表最大ID
      */
     Long getAndMaxId();

     /**
      * 获取或表最大ID
      */
     Long getOrMaxId();

     /**
      * 将结果存入最终表中
      * @param courseId
      * @param strategyId
      * @param strategyName
      */
     void combineAllStrategy(Long courseId,Byte strategySerial,String strategyName,Long strategyId);

     /**
      * 获取相应课程的serial
      * @param courseId
      */
     ArrayList<Byte> getSerialList(Long courseId);
}
