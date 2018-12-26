package com.gugu.gugumodel.dao;

import com.gugu.gugumodel.entity.*;
import com.gugu.gugumodel.entity.strategy.CourseMemberLimitStrategyEntity;
import com.gugu.gugumodel.entity.strategy.MemberLimitStrategy;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.entity.CourseEntity;
import com.gugu.gugumodel.entity.ShareMessageEntity;
import com.gugu.gugumodel.entity.SimpleCourseEntity;
import com.gugu.gugumodel.exception.NotFoundException;
import com.gugu.gugumodel.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.util.ArrayList;


@Repository
public class CourseDao{
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    KlassSeminarMapper klassSeminarMapper;
    @Autowired
    StrategyMapper strategyMapper;

    @Autowired
    RoundScoreMapper roundScoreMapper;


    /**
     * 找出与用户相关的课程信息
     * @param userId
     * @param role
     * @return
     */
    public ArrayList<SimpleCourseEntity> findSimpleCourseEntityByUserId(Long userId,String role) {
        if(role.equals("ROLE_Teacher")){
            return courseMapper.findSimpleCourseEntityByTeacherId(userId);
        }else{
            return courseMapper.findSimpleCourseEntityByStudenId(userId);
        }
    }

    /**
     * 获取所有的课程
     * @return
     */
    public ArrayList<CourseEntity> getAllCourse(){
        return courseMapper.getAllCourse();
    }

    /**
     * 新建课程
     * @param courseEntity
     * @return
     */
    public Long newCourse(CourseEntity courseEntity){
        return courseMapper.newCourse(courseEntity);
    }

    /**
     * 新建课程的组队限制规则
     * @param courseEntity
     * @return
     */
    public boolean addTeamStrategy(CourseEntity courseEntity){
        //加课程自身的规则，并获取规则ID
        MemberLimitStrategy selfLimit=courseEntity.getMemberLimitStrategy();
        strategyMapper.addMemberLimitStrategy(selfLimit);
        Long memberLimitStrategyId = selfLimit.getId();

        ArrayList<Long> idList = new ArrayList<>();
        ArrayList<CourseMemberLimitStrategyEntity> courseMemberLimitStrategyList = courseEntity.getCourseMemberLimitStrategyEntityList();

        //加入其他课程的规则信息
        for(int i=0;i<courseMemberLimitStrategyList.size();i++){
            //加入选其他课的规则，并把ID到List中
            strategyMapper.addCourseMemberLimitStrategy(courseMemberLimitStrategyList.get(i));
            Long courseMemberLimitStrategyId=courseMemberLimitStrategyList.get(i).getId();
            idList.add(courseMemberLimitStrategyId);
        }

        //按与或规则添加到相应表中，取得ID
        Long courseLimitId=null;
        String strategy_name=new String();
        if(courseEntity.isAnd()){
            //如果是“与”关系
            //获取与表最大ID
            Long andId=strategyMapper.getAndMaxId();
            andId=(andId==null)?andId=Long.parseLong("1"):andId+1;
            for(int i=0;i<idList.size();i++){
                //加入“与”表
                strategyMapper.andCourseMemberLimitStrategy(andId,idList.get(i),"CourseMemberLimitStrategy");
            }
            courseLimitId=andId;
            strategy_name="TeamAndStrategy";
        }
        else if(!courseEntity.isAnd()){
            //如果是“或关系”
            //获取或表最大ID
            Long orId=strategyMapper.getAndMaxId();
            orId=(orId==null)?orId=Long.parseLong("1"):orId+1;
            for(int i=0;i<idList.size();i++){
                //加入“或”表
               strategyMapper.orCourseMemberLimitStrategy(orId,idList.get(i),"CourseMemberLimitStrategy");
            }
            courseLimitId=orId;
            strategy_name="TeamOrStrategy";
        }

        //将课程自身规则和选其他课的规则以“与”逻辑存入表中
        Long newAndId=strategyMapper.getAndMaxId();
        newAndId=(newAndId==null)?newAndId=Long.parseLong("1"):newAndId+1;
        strategyMapper.andCourseMemberLimitStrategy(newAndId,courseLimitId,strategy_name);
        strategyMapper.andCourseMemberLimitStrategy(newAndId,memberLimitStrategyId,"MemberLimitStrategy");

        //获得strategySerial
        Byte strategySerial=this.getSerial(courseEntity);

        //将最终规则存入最终表中
        strategyMapper.combineAllStrategy(courseEntity.getId(),strategySerial,"TeamAndStrategy",newAndId);

        return true;
    }

    /**
     * 新建课程的组队限制规则
     * @param courseEntity
     * @return
     */
    public boolean addConflictStrategy(CourseEntity courseEntity){
        ArrayList<CourseEntity> conflictList=courseEntity.getConflictCourseList();
        Long maxId=strategyMapper.getConflictMaxId();
        maxId=(maxId==null)?Long.parseLong("1"):maxId+1;
        for(int i=0;i<conflictList.size();i++){
            //加入冲突的课程
            System.out.println(conflictList.get(i).getId());
            strategyMapper.addConflictStrategy(maxId,conflictList.get(i).getId());
        }
        //将本课程加入冲突列表中
        strategyMapper.addConflictStrategy(maxId,courseEntity.getId());
        return true;
    }

    /**
     * 根据课程id获取strategySerial
     * @param courseEntity
     * @return
     */
    private Byte getSerial(CourseEntity courseEntity){
        Byte strategySerial=null;
        ArrayList<Byte> serialList =strategyMapper.getSerialList(courseEntity.getId());
        if(serialList.size()==0){
            return 1;
        }
        else{
            for(Byte i=0;i<serialList.size();i++){
                if(!serialList.get(i).equals(i+1)){
                    Integer temp=i+1;
                    return Byte.parseByte(temp.toString());
                }
            }
        }
        Integer re=serialList.size()+1;
        return Byte.parseByte(re.toString());
    }

    /**
     * 根据id获取课程详细信息
     * @param id
     * @return
     */
    public CourseEntity getCourseById(Long id) {
        return courseMapper.getCourseById(id);
    }

    /**
     * 根据courseId删除课程
     * @param id
     * @throws NotFoundException
     */
    public void deleteCourseById(Long id) throws NotFoundException {
        if(getCourseById(id)!=null) {
            courseMapper.deleteCourseById(id);
        }else{
            throw new NotFoundException("找不到该记录");
        }
    }


    /**
     * 获取与课程相关的共享讨论课信息
     * @param courseId
     * @return
     */
    public ArrayList<ShareMessageEntity> getSeminarShareMessage(Long courseId) {
        Long mainCourseId=courseMapper.getSeminarMainCourseId(courseId);
        SimpleCourseEntity mainCourse=courseMapper.getSimpleCourseById(mainCourseId);
        ArrayList<ShareMessageEntity> shareMessageEntities=new ArrayList<>();
        if(mainCourseId==null){
            return shareMessageEntities;
        }
        if(mainCourseId.equals(courseId)){
            ArrayList<ShareReceiveCourseEntity> shareRecieveCourseEntities=courseMapper.getSeminarRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),2);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareReceiveCourseEntity shareReceiveCourseEntity =(ShareReceiveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareReceiveCourseEntity.setShareId(courseMapper.getShareSeminarIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId), shareReceiveCourseEntity,2);
            shareMessageEntities.add(shareMessageEntity);
        }
        return shareMessageEntities;
    }

    /**
     * 获取与课程相关的共享小组信息
     * @param courseId
     * @return
     */
    public ArrayList<ShareMessageEntity> getTeamShareMessage(Long courseId) {
        Long mainCourseId=courseMapper.getTeamMainCourseId(courseId);
        SimpleCourseEntity mainCourse=courseMapper.getSimpleCourseById(mainCourseId);
        ArrayList<ShareMessageEntity> shareMessageEntities=new ArrayList<>();
        if(mainCourseId==null){
            return shareMessageEntities;
        }
        if(mainCourseId.equals(courseId)){
            ArrayList<ShareReceiveCourseEntity> shareRecieveCourseEntities=courseMapper.getTeamRecieveCourses(courseId);
            for(int i=0;i<shareRecieveCourseEntities.size();i++){
                ShareMessageEntity shareMessageEntity=new ShareMessageEntity(mainCourse,shareRecieveCourseEntities.get(i),1);
                shareMessageEntities.add(shareMessageEntity);
            }
        }else{
            ShareReceiveCourseEntity shareReceiveCourseEntity =(ShareReceiveCourseEntity)courseMapper.getSimpleCourseById(courseId);
            shareReceiveCourseEntity.setShareId(courseMapper.getShareTeamIdByCourse(mainCourseId,courseId));
            ShareMessageEntity shareMessageEntity=new ShareMessageEntity(courseMapper.getSimpleCourseById(mainCourseId), shareReceiveCourseEntity,1);
            shareMessageEntities.add(shareMessageEntity);
        }
        return shareMessageEntities;
    }

    /**
     * @author ljy
     * @param id
     * @return
     */
    public ArrayList<Long> getCourseIdByTeacherId(long id){
        return courseMapper.getCourseIdByTeacherId(id);
    }

    /**
     * 删除共享讨论课的信息
     * @param shareId
     * @return
     */
    public boolean deleteSeminarShare(Long shareId){
        ShareApplicationEntity shareApplicationEntity=courseMapper.getSeminarShareApplicationById(shareId);
        if(shareApplicationEntity==null){
            return false;
        }
        if(courseMapper.getSeminarRecieveCourses(shareApplicationEntity.getMainCourseId()).size()==0) {
            courseMapper.deleteCourseSeminarMain(shareApplicationEntity.getMainCourseId());
        }
        courseMapper.deleteCourseSeminarMain(shareApplicationEntity.getSubCourseId());
        courseMapper.deleteSeminarShare(shareId);
        courseMapper.deleteAllSeminarByCourseId(shareApplicationEntity.getSubCourseId());
        return true;
    }

    /**
     * 删除共享分组的记录
     * @param shareId
     * @return
     */
    public boolean deleteTeamShare(Long shareId){
        ShareApplicationEntity shareApplicationEntity=courseMapper.getTeamShareApplicationById(shareId);
        if(shareApplicationEntity==null){
            return false;
        }
        courseMapper.deleteCourseTeamMain(shareApplicationEntity.getSubCourseId());
        courseMapper.deleteTeamShare(shareId);
        klassStudentMapper.deleteReceiveTeamShare(shareApplicationEntity.getSubCourseId());
        return true;
    }

    /**
     * 根据courseid获取老师id
     * @param courseId
     * @return
     */
    public Long getTeacherIdByCourse(Long courseId){
        return courseMapper.getTeacherIdByCourse(courseId);
    }

    /**
     * 删除课程下所有的讨论课
     * @param courseId
     */
    public void deleteAllSeminarByCourseId(Long courseId){
        klassSeminarMapper.deleteAllKlassSeminarByCourseId(courseId);
        courseMapper.deleteAllSeminarByCourseId(courseId);
    }

    /**
     * 获取一个课程下的所有小组
     * @param courseId
     * @return
     */
    public ArrayList<TeamEntity> getAllTeamByCourse(Long courseId){
        return teamMapper.getAllTeamByCourse(courseId);
    }
    /**
     * @author TYJ
     * 修改课程共享讨论课的状态
     * @param subCourseId
     * @param mainCourseId
     * @return
     */
    public int changeSeminarShareStatus(Long subCourseId,Long mainCourseId){
        return courseMapper.changeSeminarShareStatus(subCourseId,mainCourseId);
    }

    /**
     * @author TYJ
     * 修改课程共享分组的状态
     * @param subCourseId
     * @param mainCourseId
     * @return
     */
    public int changeTeamShareStatus(Long subCourseId,Long mainCourseId){
        return courseMapper.changeTeamShareStatus(subCourseId,mainCourseId);
    }

    /**
     * @author ljy
     * 获取小组在本轮此下的总成绩
     * @return
     */
    public ArrayList<RoundScoreEntity> getTeamTotalScoreInRound(Long courseId,Long roundId){
        return roundScoreMapper.getTeamTotalScoreInRound(courseId,roundId);
    }
}
