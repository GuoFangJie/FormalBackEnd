package com.gugu.gugumodel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KlassStudentMapper {
    public Long findTeamIdByStudentIdAndCourseId(@Param("student_id") Long student_id, @Param("course_id") Long course_id);
}
