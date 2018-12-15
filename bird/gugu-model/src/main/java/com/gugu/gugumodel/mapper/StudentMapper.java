package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
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

     void deleteStudentById(long id);

     List<StudentBasicInforVO> studentInfor();
}
