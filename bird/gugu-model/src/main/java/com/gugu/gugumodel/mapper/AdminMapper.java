package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.pojo.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author TYJ
 */
@Mapper
@Repository
public interface AdminMapper {

     /**
      * 管理员登录
      * @param adminEntity
      * @return AdminEntity
      */
     AdminEntity adminLogin(AdminEntity adminEntity);


}
