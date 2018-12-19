package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.KlassStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class KlassStudentService {
    @Autowired
    KlassStudentDao klassStudentDao;
    /**
     * 根据班级id删除班级学生记录
     */
    public boolean delete(Long klassId){
        return klassStudentDao.deleteByKlassId(klassId);
    }
}
