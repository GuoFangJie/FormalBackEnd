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
public interface ShareTeamMapper {

    /**
     * 获得共享分组信息
     * @param userId
     * @return
     */
    ArrayList<ShareApplicationEntity> getTeamShareList(Long userId);

    /**
     * 根据ID获取共享分组请求信息
     * @param requestId
     * @return
     */
    ShareApplicationEntity getTeamShareApplicationById(Long requestId);

    /**
     * 修改共享分组申请的状态
     * @param requestId
     * @param status
     * @return
     */
    void changeTeamShareStatus(Long requestId,Byte status);

    /**
     * 新建共享分组申请
     * @param mainCourseId
     * @param subCourseId
     * @param subCourseTeacher
     */
    void newShareTeamApplication(Long mainCourseId,Long subCourseId,Long subCourseTeacher);
}
