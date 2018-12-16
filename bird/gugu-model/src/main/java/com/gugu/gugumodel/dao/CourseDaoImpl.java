package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.ShareMessageEntity;
import com.gugu.gugumodel.pojo.entity.ShareRecieveCourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    CourseMapper courseMapper;
    @Override
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudentId(Long studentId) {
        return courseMapper.findSimpleCourseEntityByStudenId(studentId);
    }
    @Override
    public Long newCourse(CourseEntity courseEntity){
        return courseMapper.newCourse(courseEntity);
    }

    @Override
    public CourseEntity getCourseById(Long id) {
        return courseMapper.getCourseById(id);
    }

    @Override
    public void deleteCourseById(Long id) throws Exception {
        if(getCourseById(id)!=null) {
            courseMapper.deleteCourseById(id);
        }else{
            throw new Exception("找不到该记录");
        }
    }

    /**
     * 很关键，没测试
     * @param courseId
     * @return
     */
    @Override
    public ArrayList<ShareMessageEntity> getSeminarShareMessage(Long courseId) {
        Long mainCourseId=courseMapper.getSeminarMainCourseId(courseId);
        SimpleCourseEntity mainCourse=courseMapper.getSimpleCourseById(mainCourseId);
        ArrayList<ShareMessageEntity> shareMessageEntities=new ArrayList<>();
        if(mainCourseId.equals(courseId)){
            ArrayList<ShareRecieveCourseEntity> shareRecieveCourseEntities=courseMapper.getSeminarRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),2);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareRecieveCourseEntity shareRecieveCourseEntity=(ShareRecieveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareRecieveCourseEntity.setShareId(courseMapper.getShareSeminarIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId),shareRecieveCourseEntity,2);
            shareMessageEntities.add(shareMessageEntity);
        }
        return shareMessageEntities;
    }

    @Override
    public ArrayList<ShareMessageEntity> getTeamShareMessage(Long courseId) {
        Long mainCourseId=courseMapper.getTeamMainCourseId(courseId);
        SimpleCourseEntity mainCourse=courseMapper.getSimpleCourseById(mainCourseId);
        ArrayList<ShareMessageEntity> shareMessageEntities=new ArrayList<>();
        if(mainCourseId.equals(courseId)){
            ArrayList<ShareRecieveCourseEntity> shareRecieveCourseEntities=courseMapper.getTeamRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),1);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareRecieveCourseEntity shareRecieveCourseEntity=(ShareRecieveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareRecieveCourseEntity.setShareId(courseMapper.getShareSeminarIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId),shareRecieveCourseEntity,1);
            shareMessageEntities.add(shareMessageEntity);
        }
        return shareMessageEntities;
    }

}
