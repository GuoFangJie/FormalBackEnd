package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.KlassSeminarEntity;
import com.gugu.gugumodel.entity.SeminarScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ren
 */
@Repository
@Mapper
public interface KlassSeminarMapper {
    /**
     * 根据班级删除记录
     */
    void deleteByKlass(Long klassId);

    /**
     * 根据班级获取klassSeminar的id
     */
    ArrayList<Long> getKlassSeminarIdByKlass(Long klassId);

    /**
     * 根据班级获取klassSeminarEntity
     */
    ArrayList<KlassSeminarEntity> getKlassSeminarEntityByKlassId(Long klassId);

    /**@author ljy
     * 按照seminarId获取所有klass_seminar_id
     * @param
     * @return
     */
    ArrayList<Long> getAllKlassSeminarId(Long seminarId);


    /**@author ljy
     * 按照seminarId ,klassId获取klass_seminar_id
     * @param
     * @return
     */
    Long getKlassSeminarId(Long seminarId,Long klass_id);

    /**@author ljy
     * 按照id获取删除klass_seminar表中的记录
     * @param
     * @return
     */
    boolean deleteKlassSeminarById(Long klassSeminarId);

    /**@author ljy
     * 按照id修改班级讨论课（设置不同班级讨论课的报告提交时间）
     * @param seminarId
     * @return
     */
    public boolean setReportDDLInClass(@Param("seminarId") Long seminarId, @Param("klassId") Long klassId, @Param("date") Date date);

    /**@author ljy
     * 按照id获取班级下讨论课
     * @param seminarId
     * @return
     */
    public KlassSeminarEntity getSeminarInClass(Long seminarId, Long klassId);

    /**@author ljy
     * 设置讨论课状态
     * @param seminarId
     * @return
     */
    public boolean setSeminarStatus(@Param("seminarId") Long seminarId,@Param("classId") Long classId,@Param("status")Byte status);

    /**@author ljy
     * 设置班级下讨论课书面报告截止时间
     * @param seminarId
     * @return
     */
    public boolean setSeminarReportddl(@Param("seminarId")Long seminarId,@Param("classId")Long classId,@Param("date")Date date);



    /**
     * 根据klassSeminar获取klassid
     * @param klassSeminarId
     * @return
     */
    Long getKlassIdByKlassSeminar(Long klassSeminarId);

    /**
     * 新建班级和讨论课之间的关系
     * @param klassId
     * @param seminarId
     */
    void newKlassSeminar(Long klassId,Long seminarId);
}
