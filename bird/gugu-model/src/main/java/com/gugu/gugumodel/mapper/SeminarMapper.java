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
    public Long newSeminar(SeminarEntity seminarEntity);



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
    public boolean updateSeminar(SeminarEntity seminarEntity);

    /**@author ljy
     * 按照id删除讨论课
     * @param
     * @return
     */
    public boolean deleteSeminar(Long seminarId);


    /**@author ljy
     * 按照id获取讨论课
     * @param
     * @return
     */
    public SeminarEntity getSeminarById(Long seminarId);

    /**@author ljy
     * 按照id获取所在roundId
     * @param
     * @return
     */
    public Long getRoundId(Long seminarId);

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
    public ArrayList<Byte> getSerial(Long courseId);

    /**@author ljy
     * 根据courseId和roundId获取讨论课信息
     * @param courseId
     * @return
     */
    public ArrayList<SeminarEntity> getSeminarByCourseAndRound(Long courseId,Long roundId);
}
