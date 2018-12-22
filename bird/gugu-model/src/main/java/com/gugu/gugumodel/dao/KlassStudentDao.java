package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.KlassSeminarMapper;
import com.gugu.gugumodel.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KlassStudentDao{
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    KlassSeminarMapper klassSeminarMapper;
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

    /**
     * 根据学生id和班级id获取teamid
     * @param
     * @param studentId
     * @return
     */
    public Long getTeamIdByClassAndStudent(Long klassSeminarId,Long studentId){
        return klassStudentMapper.getTeamIdByClassAndStudent(klassSeminarMapper.getKlassIdByKlassSeminar(klassSeminarId),studentId);
    }

}
