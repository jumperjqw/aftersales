package com.zgl.aftersales.service.impl;

import com.zgl.aftersales.service.MailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class MaileImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String title, String content) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(title);
        message.setTo(to);
        message.setText(content);
        mailSender.send(message);
    }
}
