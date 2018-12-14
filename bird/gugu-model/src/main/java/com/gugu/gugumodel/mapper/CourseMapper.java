package com.gugu.gugumodel.mapper;

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
     ArrayList<SimpleCourseEntity> findSimpleCourseEntityByStudenId(Integer studentId);
}
