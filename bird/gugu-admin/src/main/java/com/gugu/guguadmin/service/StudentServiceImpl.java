package com.gugu.guguadmin.service;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.pojo.vo.StudentBasicInforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljy
 */

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    @Override
    public void deleteStudentById(long id){
        studentDao.deleteStudentById(id);
    }

    @Override
    public List<StudentBasicInforVO> studentInfor(){
        return studentDao.studentInfor();
    }
}
