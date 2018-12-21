package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.CourseDao;
import com.gugu.gugumodel.dao.StudentDao;
import com.gugu.gugumodel.dao.TeamDao;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.gugumodel.entity.TeamEntity;
import com.gugu.gugumodel.entity.TeamValidEntity;
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

    public TeamEntity getTeamMessageByTeamId(Long teamId){
        return teamDao.getTeamById(teamId);
    }

    public StudentEntity getLeaderByTeamId(Long teamId){
        return studentDao.getLeader(teamId);
    }

    public ArrayList<StudentEntity> getMemberById(Long teamId){
        return studentDao.getMembersExceptLeader(teamId);
    }

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

    public void deleteTeam(Long teamId){
        teamDao.deleteTeam(teamId);
        teamDao.deleteStudentTeamRelation(teamId);
    }

    public void addMember(Long teamId, Long studentId,HttpServletResponse httpServletResponse){
//        Long status= teamDao.getTeamValidStatus(teamId);
//        System.out.println(status);
//        if(status==1||status==null){
            httpServletResponse.setStatus(200);
            teamDao.addMember(teamId,studentId);
//        }
//        else{
//            httpServletResponse.setStatus(405);
//        }



    }

    public void removeMember(Long teamId,Long studentId){
        teamDao.removeMember(teamId,studentId);
    }


    public void teamValidRequest(TeamValidEntity teamValidEntity){
            teamValidEntity.setTeacherId(courseDao.getTeacherIdByCourse(teamValidEntity.getCourseId()));
            teamDao.teamValidRequest(teamValidEntity);
    }

    /**@author ljy
     * 新建队伍,返回队伍id
     * @param
     * @return
     */
    public Long newTeam(ArrayList<StudentEntity> memberStudents,TeamEntity teamEntity){
        return teamDao.newTeam(memberStudents,teamEntity);
    }
}
