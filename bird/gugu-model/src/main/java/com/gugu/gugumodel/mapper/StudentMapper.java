package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ljy
 */
@Mapper
@Repository
public interface StudentMapper {

     public void deleteStudentById(long id);

     ArrayList<StudentEntity> getMembers(Long teamId);

     StudentEntity getLeader(Long teamId);
}
