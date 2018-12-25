package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
    void addMember(Long teamId,Long klassId,  Long studentId);

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
     Long getStudentTeam(@Param("studentId") Long studentId,@Param("klassId") Long classId);


    /**
     * 根据学生和课程获取班级id
     * @param courseId
     * @param studentId
     * @return
     */
    Long getKlassIdByCourseAndStudent(Long courseId,Long studentId);

    /**
     * 根据teamId获得studentId
     * @param teamId
     * @return
     */
    ArrayList<Long> getStudentByTeamId(Long teamId);

    /**
     * @author TYJ
     * 删除课程下所有学生组内信息
     * @param courseId
     * @return
     */
    void removeAllMemberByCourseId(Long courseId);

    /**
     * @author TYJ
     * 更新学生的队伍信息
     * @param studentId
     * @param teamId
     * @return
     */
    void updateTeamByStudentId(Long studentId,Long teamId);

    /**
     * 删除从课程的分组共享记录
     */
    void deleteReceiveTeamShare(Long courseId);
}
