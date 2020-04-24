package com.zgl.aftersales.service;

import com.zgl.aftersales.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Resource
public interface UserService {
    void addUser(Users user);
    Users selectByUsername(String username);
}

