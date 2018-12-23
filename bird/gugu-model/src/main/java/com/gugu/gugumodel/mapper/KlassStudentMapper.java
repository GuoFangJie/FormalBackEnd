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
     * @param 
     * @param studentId
     */
    void addMember(Long teamId,Long klassId, @Param("studentId") Long studentId);

    /**
     * 根据班级和学生获取小组id
     * @param klassId
     * @param studentId
     * @return
     */
    Long getTeamIdByClassAndStudent(Long klassId,Long studentId);

    /**
     * 根据id获取学生所在小组
     * @return
     */
    public Long getStudentTeam(@Param("studentId") Long studentId,@Param("klassId") Long classId);


    /**
     * 根据学生和课程获取班级id
     * @param courseId
     * @param studentId
     * @return
     */
    Long getKlassIdByCourseAndStudent(Long courseId,Long studentId);
}
