package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.TeacherMapper;
import com.gugu.gugumodel.mapper.TeamMapper;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author ljy
 */
@Component
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public void deleteTeacherById(long id){
            teacherMapper.deleteTeacherById(id);
    }

    @Override
    public ArrayList<TeacherEntity> getTeachers(){
        return teacherMapper.getTeachers();
    }
}
