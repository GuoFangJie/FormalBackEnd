package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.KlassEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
@Mapper
public interface KlassMapper {
    ArrayList<KlassEntity> getKlassByCourseId(Long courseId);

    void newKlass(KlassEntity klassEntity);
}