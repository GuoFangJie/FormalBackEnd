package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ljy
 */
@Mapper
@Repository
public interface StudentMapper {

     /**
      * 管理员根据学生ID删除学生账号
      * @param id
      * @return
      */
     void deleteStudentById(long id);

     /**
      * @author TYJ
      * 删除学生账号要删除学生的组队情况
      * @param studentId
      */
     void existAllTeam(Long studentId);

     ArrayList<StudentEntity> searchStudent(String identity);


     ArrayList<StudentEntity> getMembers(Long teamId);

     StudentEntity getLeader(Long teamId);

     ArrayList<StudentEntity> getStudentWithoutTeam(Long courseId);

     StudentEntity getStudentById(Long studentId);

     ArrayList<StudentEntity> getStudents();

     void resetStudentPassword(Long studentId);

     void changeStudentInformation(StudentEntity studentEntity);

     void changePassword(String password,Long studentId);

     void changeEmail(String email,Long studentId);
}
