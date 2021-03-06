package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.SeminarEntity;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.SeminarEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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


    /**@author ljy
     * 新建讨论课,创建成功后返回seminarId
     * @return Long
     */
    Long newSeminar(SeminarEntity seminarEntity);



    /**@author ljy
     * 获取讨论课所属的班级
     * @param seminarId
     * @return KlassEntiry
     */
     ArrayList<Long> getKlassIdBySeminerId(Long seminarId);

    /**@author ljy
     * 按照id修改讨论课
     * @param seminarEntity
     * @return
     */
    boolean updateSeminar(SeminarEntity seminarEntity);

    /**@author ljy
     * 按照id删除讨论课
     * @param
     * @return
     */
    boolean deleteSeminar(Long seminarId);


    /**@author ljy
     * 按照id获取讨论课
     * @param
     * @return
     */
    SeminarEntity getSeminarById(Long seminarId);

    /**@author ljy
     * 按照id获取所在roundId
     * @param
     * @return
     */
    Long getRoundId(Long seminarId);

    /**@author TYJ
     * 获取一个课程下所有的讨论课
     * @param courseId
     * @return
     */
    ArrayList<SeminarEntity> getSeminarByCourseId(Long courseId);


    /**@author ljy
     * 获取一个课程下已有的serial
     * @param courseId
     * @return
     */
    ArrayList<Byte> getSerial(Long courseId);

    /**@author ljy
     * 根据courseId和roundId获取讨论课信息
     * @param courseId
     * @return
     */
    ArrayList<SeminarEntity> getSeminarByCourseAndRound(Long courseId,Long roundId);

    /**@author ljy
     * 根据klassSeminarId获取seminar
     * @param klassSeminarId
     * @return
     */
    SeminarEntity getSeminarByKlassSeminarId(@Param("klassSeminarId") Long klassSeminarId,@Param("courseId") Long courseId);

    /**
     * 根据klassSeminarid查找seminarName
     */
    String getSeminarName(Long klassSeminarId);

    /**
     * @author TYJ
     * 删除课程下所有的seminar
     * @param courseId
     * @return
     */
    void deleteAllSeminarByCourseId(Long courseId);
}
