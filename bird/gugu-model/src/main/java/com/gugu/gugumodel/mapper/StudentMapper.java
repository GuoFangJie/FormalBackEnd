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

     void deleteStudentById(long id);

     /**
      * 管理员根据学生或姓名查找学生账号
      * @param identity
      * @return ArrayList<StudentEntity>
      */
     ArrayList<StudentEntity> searchStudent(String identity);
}
