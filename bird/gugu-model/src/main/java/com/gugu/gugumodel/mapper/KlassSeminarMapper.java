package com.gugu.gugumodel.mapper;

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

    /**@author ljy
     * 按照seminarId获取klass_seminar_id
     * @param
     * @return
     */
    Long getKlassSeminarId(Long seminarId);

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
}
