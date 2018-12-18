package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeacherDao;
import com.gugu.gugumodel.pojo.entity.ShareApplicationEntity;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import com.gugu.gugumodel.pojo.entity.TeacherEntity;
import com.gugu.guguuser.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author TYJ
 */
@Service
public class ShareService {
    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareList(Long userId){
        return null;
    }
}
