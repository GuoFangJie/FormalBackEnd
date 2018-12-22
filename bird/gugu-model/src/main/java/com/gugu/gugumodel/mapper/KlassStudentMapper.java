package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KlassStudentMapper {
    /**
     * 根据学生id和课程id获取小组id
     * @param student_id
     * @param course_id
     * @return
     */
    Long findTeamIdByStudentIdAndCourseId(@Param("student_id") Long student_id, @Param("course_id") Long course_id);

    /**
     * 新增学生和班级的关联记录
     * @param klassId
     * @param studentId
     * @param courseId
     */
    void newStudentToClass(Long klassId,Long studentId,Long courseId);

    /**
     * 根据班级删除klassStudent的记录
     */
    void deleteByKlass(Long klassId);


    /**@author ljy
     * 添加成员
     * @param teamEntity
     * @param studentId
     */
    void addMember(@Param("team") TeamEntity teamEntity, @Param("studentId") Long studentId);

    Long getTeamIdByClassAndStudent(Long klassId,Long studentId);
}
