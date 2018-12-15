package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.mapper.StudentMapper;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;

import java.util.List;


/**
 * @author ljy
 */
@Component
public class StudentDaoImpl implements StudentDao {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public void deleteStudentById(long id){
        studentMapper.deleteStudentById(id);
    }

    @Override
    public ArrayList<StudentEntity> searchStudent(String identity){
        return studentMapper.searchStudent(identity);
    }

    @Override
    public List<StudentBasicInforVO> studentInfor(){
       return studentMapper.studentInfor();
    }
}
