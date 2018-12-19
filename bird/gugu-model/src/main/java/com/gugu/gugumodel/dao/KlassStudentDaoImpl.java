package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KlassStudentDaoImpl implements KlassStudentDao{
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Override
    public Long getTeamId(Long student_id, Long course_id) {
        return klassStudentMapper.findTeamIdByStudentIdAndCourseId(student_id,course_id);
    }

    public void newStudentToClass(Long klassId,Long studentId,Long courseId){
        klassStudentMapper.newStudentToClass(klassId,studentId,courseId);
    }
    /**
     * 根据班级删除记录
     */
    public boolean deleteByKlassId(Long klassId){
        klassStudentMapper.deleteByKlass(klassId);
        return true;
    }
}
