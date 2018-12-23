package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.exception.ParamErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TYJ
 */
@Service
public class TeamRequestService {

    @Autowired
    TeamRequestDao teamRequestDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    TeamDao teamDao;

    @Autowired
    KlassDao klassDao;

    @Autowired
    StudentDao studentDao;

    /**
     * 获得组队申请信息
     * @param teacherId
     * @return
     */
    public ArrayList<Map> getTeamRequestList(Long teacherId){
        ArrayList<TeamValidEntity> teamRequestList=teamRequestDao.getTeamRequestList(teacherId);
        ArrayList <Map> teamMessageList=new ArrayList<Map>();
        for(int i=0;i< teamRequestList.size();i++){
            Map teamMessage = new HashMap();
            TeamValidEntity teamRequest=teamRequestList.get(i);
            if(teamRequest==null){
                continue;
            }
            CourseEntity course=courseDao.getCourseById(teamRequest.getCourseId());
            KlassEntity klass=klassDao.getKlassById(teamRequest.getClassId());
            StudentEntity leader=studentDao.getStudentById(teamRequest.getLeaderId());
            if(teamRequest==null&&course==null&&klass==null&&leader==null){
                continue;
            }
            String courseName=course.getCourseName();
            Byte klassSerial=klass.getKlassSerial();
            teamMessage.put("requestId",teamRequest.getId());
            teamMessage.put("courseId",teamRequest.getCourseId());
            teamMessage.put("courseName",courseName);
            teamMessage.put("klassId",teamRequest.getClassId());
            teamMessage.put("klassSerial",klassSerial);
            teamMessage.put("leaderId",leader.getId());
            teamMessage.put("leaderName",leader.getStudentName());
            teamMessage.put("reason",teamRequest.getReason());
            teamMessage.put("status",teamRequest.getStatus());
            teamMessageList.add(teamMessage);
        }
        return teamMessageList;
    }

    /**
     * 修改组队申请的状态
     * @param requestId
     * @param handleType
     * @return
     */
    public boolean changeTeamRequestStatus(Long requestId,String handleType) throws ParamErrorException {
        Byte status;
        TeamValidEntity teamValidEntity=teamRequestDao.getTeamRequestById(requestId);
        if(handleType.equals("accept")){
            status=1;
        }
        else if(handleType.equals("refuse")){
            status=0;
        }
        else{
            throw new ParamErrorException("请求参数错误（必须为accept/refuse）");
        }
        teamDao.changeTeamStatus(teamValidEntity.getTeamId(),status);
        teamRequestDao.changeTeamRequestStatus(requestId,status);
        return true;
    }
}
