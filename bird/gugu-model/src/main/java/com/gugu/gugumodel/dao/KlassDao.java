package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassMapper;
import com.gugu.gugumodel.entity.KlassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Repository
public class KlassDao{
    @Autowired
    KlassMapper klassMapper;
    public ArrayList<KlassEntity> getKlassByCourseId(Long courseId) {
        return klassMapper.getKlassByCourseId(courseId);
    }

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

    /**
     * 根据班级id删除班级
     */
    public boolean deleteKlassById(Long klassId){
       if(klassMapper.getKlassById(klassId)==null){
           return false;
       }else{
           klassMapper.deleteKlassById(klassId);
       }
        return true;
    }
}
