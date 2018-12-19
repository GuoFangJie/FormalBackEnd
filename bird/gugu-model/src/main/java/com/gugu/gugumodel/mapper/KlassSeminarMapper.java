package com.gugu.gugumodel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
}
