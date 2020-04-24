package com.zgl.aftersales;

import com.zgl.aftersales.pojo.Users;
import com.zgl.aftersales.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class AftersalesApplicationTests {
   @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Autowired
    UserService userService;
    @Test
    void addUser(){
        Users user=new Users();
        user.setUser_name("xw");
        user.setPassword("123");
        user.setEmail("979563197@qq.com");
        user.setTask_num(0);
        user.setTel("2304153");
        userService.addUser(user);
    }

    @Test
    void selectByUsername(){
        String username="zgl";
        Users user=userService.selectByUsername(username);
        System.out.println(user);
    }

}
