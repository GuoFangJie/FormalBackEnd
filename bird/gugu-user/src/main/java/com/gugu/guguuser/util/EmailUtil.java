package com.gugu.guguuser.util;

import com.gugu.guguuser.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 *
 */
@Component
public class EmailUtil {
    @Autowired
    JavaMailSender mailSender;
    public boolean sendSimpleEmail(String topic,String content,ArrayList<String> recepients)
    {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("24320162202894@stu.xmu.edu.cn");
            message.setSubject(topic);
            message.setText(content);
            for (int i = 0; i < recepients.size(); i++) {
                message.setTo(recepients.get(i));
                mailSender.send(message);
            }
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean sendSimpleEmail(String topic,String content,String recepients)
    {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("24320162202894@stu.xmu.edu.cn");
            message.setSubject(topic);
            message.setText(content);
            message.setTo(recepients);
        }catch (Exception e)
        {
            System.out.println(e);
            return false;
        }
        return true;
    }
}