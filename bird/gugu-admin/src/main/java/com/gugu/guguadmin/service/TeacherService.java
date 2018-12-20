package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.CourseDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.entity.TeacherEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ljy
 */

@Service
public class TeacherService{
    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private CourseDao courseDao;

    /**
     * 管理员根据教师ID删除教师账号
     * @param id
     * @return
     */
    public void deleteTeacherById(long id) throws NotFoundException {
        teacherDao.deleteTeacherById(id);
        ArrayList<Long> list=courseDao.getCourseIdByTeacherId(id);
        for(int i=0;i<list.size();i++){
            courseDao.deleteCourseById(list.get(i));
        }
    }

    /**
     * 管理员获取所有教师信息
     * @return ArrayList
     */
    public ArrayList<TeacherEntity> getTeachers(){
        return teacherDao.getTeachers();
    }


    /**
     * @author ljy
     * 管理员重置教师密码
     * @param teacherId
     */
    public void resetTeacherPassword(Long teacherId){
        teacherDao.resetTeacherPassword(teacherId);
    }


    /**
     * @author ljy
     * 管理员修改教师信息，包括账号，姓名，邮箱
     * @param teacherEntity
     */
    public void changeTeacherInformation (TeacherEntity teacherEntity){
        teacherDao.changeTeacherInformation(teacherEntity);
    }


    /**
     * @author ljy
     * 管理员新建教师账号
     * @param teacherEntity
     * @return TeacherEntity
     */
    public Long newTeacher (TeacherEntity teacherEntity){
        return teacherDao.newTeacher(teacherEntity);
    }


    /**
     * @author TYJ
     * 管理员根据教工号或名字搜索教师
     * @param identity
     * @return Arraylist
     */
    public ArrayList<TeacherEntity> searchTeacher(String identity){
        if("".equals(identity)){
            return teacherDao.getTeachers();
        }
        else{
            return teacherDao.searchTeacher(identity);
        }
    }
}
