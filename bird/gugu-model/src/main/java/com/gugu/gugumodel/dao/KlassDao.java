package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.pojo.entity.KlassEntity;

import java.util.ArrayList;

/**
 * @author ren
 */
public interface KlassDao {
    ArrayList<KlassEntity> getKlassByCourseId(Long courseId);

    Long newKlass(KlassEntity klassEntity);
}
