package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.CourseDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ljy
 */

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;
    CourseDao courseDao;

    @Override
    public void deleteTeacherById(long id)throws Exception{
        teacherDao.deleteTeacherById(id);
        ArrayList<Long> list=courseDao.getCourseIdByTeacherId(id);
        for(int i=0;i<list.size();i++){
            courseDao.deleteCourseById(list.get(i));
        }
    }

    @Override
    public ArrayList<TeacherEntity> getTeachers(){
        return teacherDao.getTeachers();
    }

    @Override
    public void resetTeacherPassword(Long teacherId){
        teacherDao.resetTeacherPassword(teacherId);

    }

    @Override
    public void changeTeacherInformation(TeacherEntity teacherEntity){
        teacherDao.changeTeacherInformation(teacherEntity);
    }

    @Override
    public void newTeacher(TeacherEntity teacherEntity){
        teacherDao.newTeacher(teacherEntity);
    }
}
