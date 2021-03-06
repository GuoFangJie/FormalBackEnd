package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.StudentEntity;
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
     * @param
     * @return
     */
    Long findTeamIdByStudentIdAndCourseId(Long student_id,Long courseId);

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
    void addMember(@Param("teamId") Long teamId,@Param("studentId") Long studentId);

    /**
     * 根据班级和学生获取小组id
     * @param klassId
     * @param studentId
     * @return
     */
    Long getTeamIdByClassAndStudent(Long klassId,Long studentId);

    /**
     * 获取同班小组成员
     * @param teamId
     * @return
     */
    ArrayList<Long> getKlassMember(Long teamId,Long courseId);

    /**
     * 根据id获取学生所在小组
     * @return
     */
    Long getStudentTeam(Long studentId);


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
     * @author ljy
     *删除klass_team表中的联系
     * @param teamId
     * @return
     */
    void removeKlassTeamRelation(Long teamId);

    /**
     * @author ljy
     *删除team_student表中的联系
     * @param teamId
     * @return
     */
    void removeStudentTeamRelation(Long teamId);


    /**
     * @author ljy
     *获取课程下的所有学生
     * @param courseId
     * @return
     */
    ArrayList<StudentEntity> getStudentInCourse(Long courseId);

    /**
     * @author ljy
     *根据学生id和课程id获取teamid
     * @param studentId
     * @return
     */
    Long getTeamIdByStudentAndCourse(@Param("studentId") Long studentId,@Param("courseId") Long courseId);

    /**
     * 删除从课程的分组
     * @param courseId
     */
    void deleteReceiveTeamShare(Long courseId);

    /**
     * 获取未组队的成员信息
     * @param courseId
     * @param studentId
     * @return
     */
    ArrayList<StudentEntity> getStudentWithoutTeam(Long courseId,Long studentId);
}
