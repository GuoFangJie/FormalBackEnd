package com.gugu.gugumodel.mapper;

import com.gugu.gugumodel.entity.AdminEntity;
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
      * @param account
      * @return AdminEntity
      */
     AdminEntity adminLogin(String account);


}
