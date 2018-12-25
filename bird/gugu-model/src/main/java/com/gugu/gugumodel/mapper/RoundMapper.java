package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.RoundEntity;
import com.gugu.gugumodel.entity.RoundScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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

    /**
     * 修改round信息，主要是三部分计分的方式
     * @param roundEntity
     */
    void editRoundMessage(RoundEntity roundEntity);
    /**
     * 新建round
     */
    void newRound(RoundEntity roundEntity);

    /**
     * 获取一个课程下所有的轮次
     * @param courseId
     * @return
     */
    ArrayList<RoundEntity> getRoundMessageByCourseId(Long courseId);

    /**@author ljy
     * 设置讨论课轮次
     * @return
     */
    public boolean setSeminarRound(@Param("roundId") Long roundId, @Param("roundEntity") RoundEntity roundEntity);

    /**@author ljy
     * 根据roundId获取roundSerial
     */
    Byte getRoundSerialById(Long roundId);

    /**
     * @author TYJ
     * 删除课程下所有的轮次
     */
    void deleteAllRoundByCourseId(Long courseId);
    /**
     * @author ljy
     * 获取当前课程下已有的round_serial
     */
    public ArrayList<Byte> getSerial(Long courseId);
}
