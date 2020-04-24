package com.zgl.aftersales.dao;

import com.zgl.aftersales.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //插入用户
    void addUser(Users user);
    Users selectByUsername(String username);

}
