package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassMapper;
import com.gugu.gugumodel.pojo.entity.KlassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class KlassDaoImpl implements KlassDao {
    @Autowired
    KlassMapper klassMapper;
    @Override
    public ArrayList<KlassEntity> getKlassByCourseId(Long courseId) {
        return klassMapper.getKlassByCourseId(courseId);
    }

    @Override
    public Long newKlass(KlassEntity klassEntity) {
        klassMapper.newKlass(klassEntity);
        return klassEntity.getId();
    }

    /**
     * 根据班级获取课程id
     */
    public Long getCourseIdByKlass(Long klassId){
        return klassMapper.getCourseIdByKlass(klassId);
    }
}
