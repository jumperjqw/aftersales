package com.zgl.aftersales.service.impl;


import com.zgl.aftersales.dao.QuestionMapper;
import com.zgl.aftersales.pojo.Question;
import com.zgl.aftersales.pojo.Users;
import com.zgl.aftersales.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class QuestionImpl implements QuestionService {
    @Autowired
    QuestionMapper db;
    @Override
    public int addQuestion(Question question) {
        return db.addQuestion(question);
    }

    @Override
    public Question checkQuestion(int Question_id) {
        return db.checkQuestion(Question_id);
    }

//    @Override
//    public int DeleteUser(int User_id) {
//        return db.DeleteUser(User_id);
//    }

    @Override
    public Users checkPostMan(int User_id) {
        return db.checkPostMan(User_id);
    }
    @Override
    public int updateUser(List<Users> list) {
        return db.updateUser(list);
    }





}
