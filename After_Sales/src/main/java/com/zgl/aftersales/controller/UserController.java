package com.zgl.aftersales.controller;


import com.alibaba.fastjson.JSONObject;
import com.zgl.aftersales.pojo.Status;
import com.zgl.aftersales.pojo.Users;
import com.zgl.aftersales.service.MailService;
import com.zgl.aftersales.service.UserService;
import com.zgl.aftersales.utiles.DesDecodeUtiles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
@CrossOrigin //允许跨域
@Slf4j
public class UserController {
   @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Status addUser(@RequestBody JSONObject json){
        JSONObject userJson=json.getJSONObject("user");
        Users user=new Users();
        user.setUser_name(userJson.getString("User_name"));
        user.setPassword(userJson.getString("Password"));
        user.setTel(userJson.getString("Tel"));
        user.setEmail(userJson.getString("Email"));

        String repwd=json.getString("repwd");


        System.out.println(user);
        Status status=new Status();
        Boolean flag=true;//判断注册格式是否符合要求

        String userName=user.getUser_name();
        String pwd=user.getPassword();
        String tel=user.getTel();
        String mail=user.getEmail();


        String patternUserName="^(?!\\d+$)[\\da-zA-Z_\\u4E00-\\u9FA5]+$";//不能全为数字，可以包含下划线
        String patternPwd= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$";//必须由数字和字母组成，且长度大于6
        String patternTel="^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        String patternMail="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        if(Pattern.matches(patternUserName,userName)&&!userName.equals("")){
           if(Pattern.matches(patternPwd,pwd)&&!pwd.equals("")){
               if(repwd.equals(pwd)) {

                   if (Pattern.matches(patternTel, tel) && !tel.equals("")) {
                       if (Pattern.matches(patternMail, mail) && !mail.equals("")) {
                           try {
                               userService.addUser(user);
                               status.setStatus(true);
                               status.setMsg("注册成功");
                           }
                           catch (Exception e){
                               status.setMsg("注册失败,该用户已存在" );

                           }

                       } else {
                           status.setMsg("注册失败，请输入正确邮箱");
                       }
                   } else {
                       status.setMsg("注册失败，请输入正确的电话号码");
                   }
               }else {
                   status.setMsg("注册失败，密码确认失败");
               }

               }else {
                   status.setMsg("注册失败，密码格式错误");
               }


       }else {
           status.setMsg("注册失败，用户名不符合格式");
       }

       return status;

   }

   @PostMapping("/login")
   public Status selectByUsername(@RequestBody JSONObject json){
        DesDecodeUtiles desDecodeUtiles=new DesDecodeUtiles();
        Status status=new Status();
        String loginUsername=json.getString("username");//前台传入的用户名
        String loginPwd=json.getString("pwd");//前台传入的密码

        Users user=userService.selectByUsername(loginUsername);

        if(user==null){
            status.setMsg("该用户不存在");
        }
        else {
            if(loginPwd.equals(desDecodeUtiles.getDecryptString(user.getPassword()))){
                status.setStatus(true);
                if(user.getRole_id()==1){
                   status.setMsg("登录成功，该用户为管理员");
                   status.setData("admin_login.hmtl");//role_id=1,为管理员
                }else if (user.getRole_id()==2){
                    status.setMsg("登录成功，该用户为维修人员");
                    status.setData("worker_login.hmtl");//role_id=2,为维修人员
                }else if (user.getRole_id()==3){
                    status.setMsg("登录成功，该用户为普通用户");
                    status.setData("user_login.hmtl");//role_id=3,为普通用户
                }
            }else {
                status.setMsg("登录失败，密码错误");
            }

        }

        return status;
   }

   @PostMapping("/mail")
    public Status resetPed(@RequestBody JSONObject json){
        String toMail=json.getString("mail");
        Status status=new Status();
        //生成随机验证码
       String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
       try {
           mailService.sendMail(toMail,"验证码",checkCode);

           status.setMsg("验证码发送成功");
           status.setData(checkCode);
           status.setStatus(true);
       }catch (Exception e){
           status.setMsg("验证码发送失败");
       }
        return status;
   }

}
