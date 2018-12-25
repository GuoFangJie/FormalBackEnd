package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassMapper;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.mapper.KlassRoundMapper;
import com.gugu.gugumodel.mapper.KlassSeminarMapper;
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
    @Autowired
    KlassSeminarMapper klassSeminarMapper;
    @Autowired
    KlassRoundMapper klassRoundMapper;

    /**
     * 获取课程下所有的班级信息
     * @param courseId
     * @return
     */
    public ArrayList<KlassEntity> getKlassByCourseId(Long courseId) {
        return klassMapper.getKlassByCourseId(courseId);
    }

    /**
     * 新建班级
     * @param klassEntity
     * @return
     */
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

    /**
     * 根据klass获取班级
     */
    public KlassEntity getKlassById(Long klassId){
        return klassMapper.getKlassById(klassId);
    }

    /**
     * 根据课程获取班级id列表
     * @param courseId
     * @return
     */
    public ArrayList<Long> getKlassIdByCourseId(Long courseId){
        return klassMapper.getKlassIdByCourseId(courseId);
    }

    /**
     * 新建班级与讨论课的关系
     * @param seminarId
     * @param courseId
     * @return
     */
    public boolean newKlassSeminar(Long seminarId,Long courseId){
        ArrayList<KlassEntity> longs=klassMapper.getKlassByCourseId(courseId);
        try{
            for(int i=0;i<longs.size();i++){
                klassSeminarMapper.newKlassSeminar(longs.get(i).getId(),seminarId);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 新建班级和轮次的关系
     */
    public boolean newKlassRound(Long roundId,Long courseId){
        ArrayList<KlassEntity> longs=klassMapper.getKlassByCourseId(courseId);
        try{
            for(int i=0;i<longs.size();i++){
                klassRoundMapper.newKlassRound(longs.get(i).getId(),roundId);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }



    /**@author ljy
     *获取课程下已有的serial
     * @param courseId
     */
    public ArrayList<Byte> getSerial(Long courseId){
        return klassMapper.getSerial(courseId);
    }
}
