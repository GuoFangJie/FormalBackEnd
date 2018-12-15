package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.TeacherMapper;
import com.gugu.gugumodel.mapper.TeamMapper;
import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
