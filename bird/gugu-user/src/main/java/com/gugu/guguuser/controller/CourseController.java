package com.gugu.guguuser.controller;

import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.entity.strategy.MemberLimitStrategy;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.guguuser.controller.vo.*;
import com.gugu.guguuser.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@SuppressWarnings("AliEqualsAvoidNull")
@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    KlassService klassService;
    @Autowired
    ShareService shareService;
    @Autowired
    RoundService roundService;
    @Autowired
    AttendanceService attendanceService;
    @Autowired
    QuestionService questionService;
    @Value("${loginPage}")
    String loginPage;
    /**
     * 获取与用户相关的课程
     * @param
     * @return
     */
    @GetMapping("")
    @RolesAllowed({"Teacher","Student"})
    public ArrayList<SimpleCourseVO> getCourseByUser(HttpServletRequest httpServletRequest){
        String userId=httpServletRequest.getAttribute("userId").toString();
        String role=httpServletRequest.getAttribute("role").toString();
        ArrayList<SimpleCourseEntity> simpleCourseEntities=courseService.findSimpleCourseEntityByStudentId(Long.parseLong(userId),role);
        ArrayList<SimpleCourseVO> simpleCourseVOS=new ArrayList<>();
        String role_T="ROLE_Teacher";
        if(role_T.equals(role)) {
            for(int i=0;i<simpleCourseEntities.size();i++){
                SimpleCourseVO simpleCourseVO=new SimpleCourseVO(simpleCourseEntities.get(i));
                simpleCourseVOS.add(simpleCourseVO);
            }
        }else{
            for(int i=0;i<simpleCourseEntities.size();i++){
                SimpleCourseVO simpleCourseVO=new SimpleCourseVO(simpleCourseEntities.get(i));
                simpleCourseVO.setKlassId(klassService.getKlassIdByCourseAndStudent(Long.parseLong(simpleCourseVO.getId().toString()),Long.parseLong(userId)));
                simpleCourseVOS.add(simpleCourseVO);
            }
        }
        return simpleCourseVOS;
    }

    /**
     * 获取所有的课程
     * @return
     */
    @GetMapping("/allcourse")
    public ArrayList<CourseEntity> getAllCourse(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        return courseService.getAllCourse();
    }

    /**
     * 新建课程，返回课程id
     * @param
     * @return
     */
    @PostMapping("")
    @RolesAllowed("Teacher")
    public Long newCourse(HttpServletResponse httpServletResponse,@RequestBody NewCourseVO newCourseVO,HttpServletRequest httpServletRequest) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        Long userId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        CourseEntity courseEntity=new CourseEntity();
        courseEntity.setTeacherId(userId);
        courseEntity.setCourseName(newCourseVO.getCourseName());
        courseEntity.setIntroduction(newCourseVO.getIntroduction());
        courseEntity.setPresentationPercentage(newCourseVO.getPresentationPercentage());
        courseEntity.setQuestionPercentage(newCourseVO.getQuestionPercentage());
        courseEntity.setReportPercentage(newCourseVO.getReportPercentage());
        courseEntity.setTeamStartTime(Timestamp.valueOf(newCourseVO.getTeamStartTime()));
        courseEntity.setTeamEndTime(Timestamp.valueOf(newCourseVO.getTeamEndTime()));
        //设置本课程人数限制
        MemberLimitStrategy selfStrategy=new MemberLimitStrategy();
        selfStrategy.setMinMember(newCourseVO.getMinMember());
        selfStrategy.setMaxMember(newCourseVO.getMaxMember());
        courseEntity.setMemberLimitStrategy(selfStrategy);
        //设置选修课程人数限制
        courseEntity.setCourseMemberLimitStrategyEntityList(newCourseVO.getCourseMemberLimitStrategyList());
        courseEntity.setAnd(newCourseVO.isAnd());
        //设置冲突课程
        courseEntity.setConflictCourseList(newCourseVO.getConflictCourseList());
        courseService.newCourse(courseEntity);
        return courseEntity.getId();
    }

    /**
     * 根据课程id获取课程详细信息
     * @param id
     * @return
     */
    @GetMapping("/{courseId}")
    @RolesAllowed({"Teacher","Student"})
    public CourseEntity getCourseById(@PathVariable("courseId") Long id){
        return courseService.getCourseById(id);
    }

    /**
     * 根据课程id删除课程，若不存在该课程返回404
     * @param id
     * @param httpServletResponse
     */
    @DeleteMapping("/{courseId}")
    @RolesAllowed("Teacher")
    public boolean deleteCourseById(HttpServletRequest httpServletRequest,@PathVariable("courseId") Long id, HttpServletResponse httpServletResponse) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        try {
            courseService.deleteCourseById(id);
        }catch (Exception e){
            httpServletResponse.setStatus(404,e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取小组所有讨论课成绩
     * @param courseId
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/{courseId}/score")
    @RolesAllowed({"Teacher","Student"})
    public ArrayList<SeminarScoreEntity> getTeamScoreAll(@PathVariable("courseId") Long courseId, HttpServletRequest httpServletRequest){
        Long student_id=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return courseService.getTeamAllScore(student_id,courseId);
    }

    /**
     * 获取小组的详细信息
     * @param courseId
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/{courseId}/team")
    @RolesAllowed({"Teacher","Student"})
    public TeamMessageVO getTeamMessage(@PathVariable("courseId") Long courseId,HttpServletRequest httpServletRequest){
        try {
            Long studentId = Long.parseLong(httpServletRequest.getAttribute("userId").toString());
            Long teamId = studentService.getTeamId(courseId, studentId);
            return new TeamMessageVO(courseService.getTeamById(teamId), studentService.getLeader(teamId), studentService.getMembers(teamId));
        }catch (Exception e){
            return new TeamMessageVO();
        }
    }

    /**
     * 获取课程下所有小组信息
     * @param courseId
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/{courseId}/teams")
    @RolesAllowed({"Teacher","Student"})
    public ArrayList<TeamMessageVO> getTeamsMessage(@PathVariable("courseId") Long courseId,HttpServletRequest httpServletRequest){
        ArrayList<TeamMessageVO> teamMessageVOS=new ArrayList<>();
        ArrayList<TeamEntity> teamsId=courseService.getAllTeamByCourse(courseId);
        for(int i=0;i<teamsId.size();i++){
            Integer flag=0;
            ArrayList<Long> studentList=studentService.getKlassMember(teamsId.get(i).getId(),courseId);
            ArrayList<StudentEntity> newList=new ArrayList<>();
            for(int j=0;j<studentList.size();j++){
                if(studentList.get(j).equals(teamsId.get(i).getLeaderId())){
                    flag=1;
                    continue;
                }
                newList.add(studentService.getStudentById(studentList.get(j)));
            }
            StudentEntity leader=studentService.getLeader((teamsId.get(i).getId()));
            if(flag==1){
                leader.setStudentName(leader.getStudentName());
            }
            TeamMessageVO teamMessageVO=new TeamMessageVO(teamsId.get(i),leader,newList);
            teamMessageVOS.add(teamMessageVO);
        }
        return teamMessageVOS;
    }

    /**
     * 获取同一个课程下面未组队的同学，不包括自己
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/noTeam")
    @RolesAllowed({"Teacher","Student"})
    public ArrayList<StudentEntity> getStudentWithoutTeamInCourse(@PathVariable("courseId") Long courseId, HttpServletRequest httpServletRequest){
        Long studentId=Long.parseLong(httpServletRequest.getAttribute("userId").toString());
        return studentService.getStudentWithoutTeamInCourse(courseId,studentId);
    }

    /**
     * 获取课程下的班级信息
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/class")
    @RolesAllowed({"Teacher","Student"})
    public ArrayList<KlassEntity> getKlassByCourseId(@PathVariable("courseId") Long courseId){
        return courseService.getKlassByCourseId(courseId);
    }

    /**
     * 在课程下新建班级并可以导入学生名单（后半部分还没完成）
     * @param courseId
     * @param klassEntity
     * @return
     */
    @PostMapping("/{courseId}/class")
    @RolesAllowed("Teacher")
    public Long newKlass(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@PathVariable("courseId") Long courseId,@RequestBody KlassEntity klassEntity) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        klassEntity.setCourseId(courseId);
        return klassService.newKlass(klassEntity);
    }

    /**
     * 获取该课程所有相关的共享信息
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/share")
    @RolesAllowed({"Teacher","Student"})
    public ArrayList<Map> getAllShareMessage(@PathVariable("courseId") Long courseId){
        return shareService.getShareListByCourseId(courseId);
    }

    /**
     * 删除共享关系
     * 1 为讨论课 2 为小组
     * @param
     * @param
     * @return
     */
    @RolesAllowed("Teacher")
    @DeleteMapping("/{courseId}/share/{shareId}")
    public boolean deleteCourseShare(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,@PathVariable("shareId") Long shareId,Integer type) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        return courseService.deleteCourseShare(shareId,type);
    }

    /**
     * 获取一个课程下的所有轮次
     * @param courseId
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/{courseId}/round")
    public ArrayList<RoundEntity> getRoundMessageByCourseId(@PathVariable("courseId")Long courseId) throws NotFoundException {
        return roundService.getRoundMessageByCourseId(courseId);
    }

    /**
     * 新建申请
     * @param httpServletResponse
     * @param mainCourseId
     * @param
     * @param
     */
    @RolesAllowed("Student")
    @PostMapping("/{courseId}/application")
    public void newApplication(HttpServletResponse httpServletResponse,@PathVariable("courseId") Long mainCourseId,@RequestBody SubCourseVO subCourseVO){
        try {
                courseService.newApplication(mainCourseId,subCourseVO.getSubCourseId(),subCourseVO.getType());
        }catch (Exception e){
            e.printStackTrace();
            httpServletResponse.setStatus(400,e.getMessage());
        }
    }


    /**
     * 获取特定课程下每个轮次每个小组的总分以及在该轮下每个讨论课下的成绩
     * @param roundId
     * @param courseId
     * @return
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/{courseId}/round/{roundId}")
    public RoundTeamsScoreMessageVO getTeamScoreInRound(@PathVariable("courseId") Long courseId,@PathVariable("roundId")Long roundId){
        return courseService.getTeamScoreInRound(courseId,roundId);
    }

    /**
     * 获取课程是否为共享讨论课主课程
     */
    @RolesAllowed({"Teacher","Student"})
    @GetMapping("/{courseId}/isMainSeminar")
    public boolean isMainSeminar(@PathVariable ("courseId")Long courseId){
        CourseEntity courseEntity=courseService.getCourseById(courseId);
        if(courseEntity.getSeminarMainCourseId()==null||courseEntity.getSeminarMainCourseId().equals(courseId)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取是否是共享组队的主课程
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}/isMainTeam")
    @RolesAllowed({"Teacher","Student"})
    public boolean isMainTeam(@PathVariable("courseId") Long courseId){
        CourseEntity courseEntity=courseService.getCourseById(courseId);
        if(courseEntity.getTeamMainCourseId()==null||courseEntity.getTeamMainCourseId().equals(courseId)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 给报告打分
     * @param courseId
     * @param roundId
     * @param teamId
     * @param klassSeminarId
     * @param score
     */
    @PutMapping("/{courseId}/round/{roundId}/team/{teamId}/klassSeminar/{klassSeminarId}/report")

    @Secured("ROLE_Teacher")
    public void setReportScore(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@PathVariable("courseId") Long courseId,@PathVariable("roundId") Long roundId,@PathVariable("teamId")Long teamId,@PathVariable("klassSeminarId") Long klassSeminarId,@RequestParam("score") Float score) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        attendanceService.setReportScore(courseId,roundId,klassSeminarId,teamId,score);
    }

    /**
     * 给展示打分
     * @param courseId
     * @param roundId
     * @param teamId
     * @param klassSeminarId
     * @param score
     */
    @RolesAllowed({"Teacher"})
    @PutMapping("/{courseId}/round/{roundId}/team/{teamId}/klassSeminar/{klassSeminarId}/presentation")
    public void setPresentationScore(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,@PathVariable("courseId") Long courseId,@PathVariable("roundId") Long roundId,@PathVariable("teamId")Long teamId,@PathVariable("klassSeminarId") Long klassSeminarId,@RequestParam("score") Float score) throws IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        attendanceService.setPresentationScore(courseId,roundId,klassSeminarId,teamId,score);
    }

    /**
     * 给提问打分
     * @param questionId
     * @param courseId
     * @param roundId
     * @param teamId
     * @param klassSeminarId
     * @param score
     * @throws NotFoundException
     */
    @RolesAllowed("Teacher")
    @PutMapping("/{courseId}/round/{roundId}/team/{teamId}/klassSeminar/{klassSeminarId}/question/{questionId}")
    public void setQuestionScore(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@PathVariable("questionId")Long questionId,@PathVariable("courseId") Long courseId,@PathVariable("roundId") Long roundId,@PathVariable("teamId")Long teamId,@PathVariable("klassSeminarId") Long klassSeminarId,@RequestParam("score") Float score) throws NotFoundException, IOException {
        if(!httpServletRequest.getAttribute("role").toString().equals("ROLE_Teacher")){
            httpServletResponse.setStatus(403);
        }
        questionService.scoreQuestion(questionId,score,courseId,roundId,klassSeminarId,teamId);
    }

}
