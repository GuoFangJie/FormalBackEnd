package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.pojo.vo.ActiveUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;

    /**
     * 激活老师账号
     * @param activeUserVO
     * @return
     */
    public boolean activeTeacher(ActiveUserVO activeUserVO){
        return teacherDao.activeTeacher(activeUserVO);
    }
}
