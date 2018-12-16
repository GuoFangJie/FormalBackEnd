package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.CourseEntity;
import com.gugu.gugumodel.pojo.entity.SimpleCourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ren
 */
@Mapper
@Repository
public interface CourseMapper {
     ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudenId(Long studentId);

     Long newCourse(CourseEntity courseEntity);

     CourseEntity getCourseById(Long id);

     void deleteCourseById(Long id);

     ArrayList<Long> getCourseIdByTeacherId(long id);
}
