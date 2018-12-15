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
}
