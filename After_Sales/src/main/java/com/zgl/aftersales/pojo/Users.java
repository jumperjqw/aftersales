package com.zgl.aftersales.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int User_id;
    private String User_name;
    private String Password;
    private String Tel;
    private String Email;
    private int Task_num;
    private int Role_id;

}
