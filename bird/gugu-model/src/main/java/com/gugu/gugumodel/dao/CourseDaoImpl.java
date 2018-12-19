package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.CourseMapper;
import com.gugu.gugumodel.pojo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Override
    public ArrayList<ShareMessageEntity> getSeminarShareMessage(Long courseId) {
        Long mainCourseId=courseMapper.getSeminarMainCourseId(courseId);
        SimpleCourseEntity mainCourse=courseMapper.getSimpleCourseById(mainCourseId);
        ArrayList<ShareMessageEntity> shareMessageEntities=new ArrayList<>();
        if(mainCourseId.equals(courseId)){
            ArrayList<ShareReceiveCourseEntity> shareRecieveCourseEntities=courseMapper.getSeminarRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),2);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareReceiveCourseEntity shareReceiveCourseEntity =(ShareReceiveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareReceiveCourseEntity.setShareId(courseMapper.getShareSeminarIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId), shareReceiveCourseEntity,2);
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
            ArrayList<ShareReceiveCourseEntity> shareRecieveCourseEntities=courseMapper.getTeamRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),1);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareReceiveCourseEntity shareReceiveCourseEntity =(ShareReceiveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareReceiveCourseEntity.setShareId(courseMapper.getShareTeamIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId), shareReceiveCourseEntity,1);
            shareMessageEntities.add(shareMessageEntity);
        }
        return shareMessageEntities;
    }

    /**
     * @author ljy
     * @param id
     * @return
     */
    @Override
    public ArrayList<Long> getCourseIdByTeacherId(long id){
        return courseMapper.getCourseIdByTeacherId(id);
    }

    public boolean deleteSeminarShare(Long shareId){
        ShareApplicationEntity shareApplicationEntity=courseMapper.getSeminarShareApplicationById(shareId);
        if(shareApplicationEntity==null){
            return false;
        }
        if(courseMapper.getSeminarRecieveCourses(shareApplicationEntity.getMainCourseId()).size()==0) {
            courseMapper.deleteCourseSeminarMain(shareApplicationEntity.getMainCourseId());
        }
        courseMapper.deleteCourseSeminarMain(shareApplicationEntity.getSubCourseId());
        courseMapper.deleteSeminarShare(shareId);
        courseMapper.deleteAllSeminarByCourseId(shareApplicationEntity.getSubCourseId());
        return true;
    }

    public boolean deleteTeamShare(Long shareId){
        ShareApplicationEntity shareApplicationEntity=courseMapper.getTeamShareApplicationById(shareId);
        if(shareApplicationEntity==null){
            return false;
        }
        if(courseMapper.getTeamRecieveCourses(shareApplicationEntity.getMainCourseId()).size()==0) {
            courseMapper.deleteCourseTeamMain(shareApplicationEntity.getMainCourseId());
        }
        courseMapper.deleteCourseTeamMain(shareApplicationEntity.getSubCourseId());
        courseMapper.deleteTeamShare(shareId);
        courseMapper.deleteAllTeamByCourseId(shareApplicationEntity.getSubCourseId());
        return true;
    }
}
