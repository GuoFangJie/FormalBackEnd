package com.gugu.gugumodel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KlassStudentMapper {
    Long findTeamIdByStudentIdAndCourseId(@Param("student_id") Long student_id, @Param("course_id") Long course_id);

    /**
     * 新增学生和班级的关联记录
     * @param klassId
     * @param studentId
     * @param courseId
     */
    void newStudentToClass(Long klassId,Long studentId,Long courseId);
}
