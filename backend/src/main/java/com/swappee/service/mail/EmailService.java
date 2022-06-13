package com.swappee.service.mail;


import org.springframework.mail.SimpleMailMessage;

import javax.mail.SendFailedException;

public interface EmailService {
    void sendEmail(String to, String subject, String text) throws SendFailedException;
     void sendEmail(SimpleMailMessage email) throws SendFailedException;
}
