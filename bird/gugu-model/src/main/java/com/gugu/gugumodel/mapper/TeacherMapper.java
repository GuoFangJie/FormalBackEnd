package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author ljy
 */

@Mapper
@Repository
public interface TeacherMapper{
        void deleteTeacherById(long id);

        ArrayList<TeacherEntity> getTeachers();

}
