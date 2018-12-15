package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ljy
 */

@Mapper
@Repository
public interface TeacherMapper{
        public void deleteTeacherById(long id);
}
