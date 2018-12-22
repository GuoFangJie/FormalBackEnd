package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDao;
import com.gugu.gugumodel.dao.KlassDao;
import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeamRequestDao;
import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
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
    TeamRequestDao teamMessageDao;

    @Autowired
    CourseDao courseDao;

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
        ArrayList<TeamValidEntity> teamRequestList=teamMessageDao.getTeamRequestList(teacherId);
        ArrayList <Map> teamMessageList=new ArrayList<Map>();
        for(int i=0;i< teamRequestList.size();i++){
            Map teamMessage = new HashMap();
            TeamValidEntity teamRequest=teamRequestList.get(i);
            teamMessage.put("requestId",teamRequest.getId());
            CourseEntity course=courseDao.getCourseById(teamRequest.getCourseId());
            teamMessage.put("courseId",teamRequest.getCourseId());
            String courseName=course.getCourseName();
            teamMessage.put("courseName",courseName);
            KlassEntity klass=klassDao.getKlassById(teamRequest.getClassId());
            teamMessage.put("klassId",teamRequest.getClassId());
            Byte klassSerial=klass.getKlassSerial();
            teamMessage.put("klassSerial",klassSerial);
            StudentEntity leader=studentDao.getStudentById(teamRequest.getLeaderId());
            teamMessage.put("leaderId",leader.getId());
            teamMessage.put("leaderName",leader.getStudentName());
            teamMessage.put("reason",teamRequest.getReason());
            teamMessage.put("status",teamRequest.getStatus());
            teamMessageList.add(teamMessage);
        }
        return teamMessageList;
    }

}
