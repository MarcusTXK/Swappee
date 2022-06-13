package com.swappee.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;


    @Override
    public void sendEmail(String to, String subject, String text) throws SendFailedException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hello@swappee.org");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
