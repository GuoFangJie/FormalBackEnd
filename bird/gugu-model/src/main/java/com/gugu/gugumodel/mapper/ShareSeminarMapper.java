package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.ShareApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author TYJ
 */
@Mapper
@Repository
public interface ShareSeminarMapper {

    /**
     * 获得共享讨论课信息
     * @param userId
     * @return
     */
    public ArrayList<ShareApplicationEntity> getSeminarShareList(Long userId);

    /**
     * 根据ID获取共享请求信息
     * @param requestId
     * @return
     */
    public ShareApplicationEntity getSeminarShareApplicationById(Long requestId);

    /**
     * 修改共享讨论课申请的状态
     * @param requestId
     * @param status
     * @return
     */
    public void changeSeminarShareStatus(Long requestId,Byte status);

    /**
     * 新建共享讨论课申请
     * @param mainCourseId
     * @param subCourseId
     * @param subCourseTeacher
     */
    void newShareSeminarApplication(Long mainCourseId,Long subCourseId,Long subCourseTeacher);

    /**
     * 获取该课程所有相关的共享讨论课信息
     * @param courseId
     * @return
     */
    ArrayList<ShareApplicationEntity> getSeminarShareListByCourseId(Long courseId);
}
