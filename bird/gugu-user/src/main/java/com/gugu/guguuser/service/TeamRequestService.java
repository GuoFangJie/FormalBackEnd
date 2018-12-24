package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.exception.NotFoundException;
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
    public ArrayList<Map> getTeamRequestList(Long teacherId) throws NotFoundException {
        ArrayList<TeamValidEntity> teamRequestList=teamRequestDao.getTeamRequestList(teacherId);
        ArrayList <Map> teamMessageList=new ArrayList<Map>();
        for(int i=0;i<teamRequestList.size();i++){
            Map teamMessage = new HashMap();
            TeamValidEntity teamRequest=teamRequestList.get(i);
            if(teamRequest==null){
                throw new NotFoundException("找不到相应的组队申请");
            }
            TeamEntity team=teamDao.getTeamById(teamRequest.getTeamId());
            if(team==null){
                throw new NotFoundException("找不到相应的队伍信息");
            }
            CourseEntity course=courseDao.getCourseById(team.getCourseId());
            KlassEntity klass=klassDao.getKlassById(team.getKlassId());
            StudentEntity leader=studentDao.getLeader(team.getId());
            if(course==null){
                throw new NotFoundException("找不到相应的课程信息");
            }
            if(klass==null){
                throw new NotFoundException("找不到相应的班级信息");
            }
            if(leader==null){
                throw new NotFoundException("找不到相应的组长信息");
            }
            Byte klassSerial=klass.getKlassSerial();
            teamMessage.put("requestId",teamRequest.getId());
            teamMessage.put("courseId",course.getId());
            teamMessage.put("courseName",course.getCourseName());
            teamMessage.put("klassId",klass.getId());
            teamMessage.put("klassSerial",klassSerial);
            teamMessage.put("leaderId",leader.getId());
            teamMessage.put("leaderName",leader.getStudentName());
            teamMessage.put("reason",teamRequest.getReason());
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
