package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDao;
import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeamDao;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeamEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
import com.gugu.guguuser.util.SerialUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Service
public class TeamService {
    @Autowired
    TeamDao teamDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    SerialUtil serialUtil;

    /**
     * 根据teamid获取小组的信息
     * @param teamId
     * @return
     */
    public TeamEntity getTeamMessageByTeamId(Long teamId){
        return teamDao.getTeamById(teamId);
    }

    /**
     * 获取小组的队长信息
     * @param teamId
     * @return
     */
    public StudentEntity getLeaderByTeamId(Long teamId){
        return studentDao.getLeader(teamId);
    }

    /**
     * 获取小组的成员
     * @param teamId
     * @return
     */
    public ArrayList<StudentEntity> getMemberById(Long teamId){
        return studentDao.getMembersExceptLeader(teamId);
    }

    /**
     * 更新小组的信息
     * @param teamEntity
     * @param memberStudents
     */
    public void updateTeam(TeamEntity teamEntity,ArrayList<StudentEntity> memberStudents){

        //更新team表信息
        teamDao.updateTeam(teamEntity);
        //删除之前小组和组员的联系
        teamDao.deleteStudentFromTeam(teamEntity.getId());
        //新建现在小组和组员的联系
        for(int i=0;i<memberStudents.size();i++){
            teamDao.buildRelationStuAndTeam(memberStudents.get(i).getId(),teamEntity);
        }
    }

    /**
     * 根据teamid删除小组
     * @param teamId
     */
    public void deleteTeam(Long teamId){
        teamDao.deleteTeam(teamId);
        teamDao.deleteStudentTeamRelation(teamId);
    }

    /**
     * 新加小组成员
     * @param teamId
     * @param studentId
     * @param courseId
     * @return
     */
    public Byte addMember(Long teamId, Long studentId,Long courseId){
        System.out.println("课程id为"+courseId);
            teamDao.addMember(teamId,studentId);
            Byte status=0;
            if(teamDao.teamIsLeagal(courseId,teamId)){
                status=1;
            }
            else{
                status=0;
            }
            teamDao.setStatus(teamId,status);
        System.out.println("团队的状态是："+status);
            return status;
    }

    /**
     * 移除小组成员
     * @param teamId
     * @param studentId
     * @param courseId
     * @return
     */
    public Byte removeMember(Long teamId,Long studentId,Long courseId){
        teamDao.removeMember(teamId,studentId);
        Byte status=0;
        if(teamDao.teamIsLeagal(courseId,teamId)){
            status=1;
        }
        else{
            status=0;
        }
        System.out.println("团队的状态是："+status);
        teamDao.setStatus(teamId,status);
        return status;
    }


    /**
     * 新建小组申请
     * @param teamValidEntity
     */
    public void teamValidRequest(TeamValidEntity teamValidEntity){
            teamValidEntity.setTeacherId(courseDao.getTeacherIdByCourse(teamValidEntity.getCourseId()));
            teamDao.teamValidRequest(teamValidEntity);

    }
    /**
     * 新建小组申请
     * @param
     */
    public void setTeamStatus(Long teamId){
        teamDao.setTeamStatus(teamId);
    }


    /**@author ljy
     * 新建队伍,返回队伍id
     * @param
     * @return
     */
    public Long newTeam(ArrayList<StudentEntity> memberStudents,TeamEntity teamEntity){
        //新增了计算team_serial
        ArrayList<Byte> teamSerial=teamDao.getSerial(teamEntity.getKlassId());
        serialUtil.setSerialList(teamSerial);
        teamEntity.setTeamSerial(serialUtil.calcuSerial());
        if(teamDao.teamIsLeagal(teamEntity.getCourseId(),teamEntity.getId())){
            teamEntity.setStatus(1);
        }
        else {
            teamEntity.setStatus(0);
        }

        return teamDao.newTeam(memberStudents,teamEntity);
    }
}
