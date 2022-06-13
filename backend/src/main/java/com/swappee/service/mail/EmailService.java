package com.swappee.service.mail;


import javax.mail.SendFailedException;

public interface EmailService {
    void sendEmail(String to, String subject, String text) throws SendFailedException;
}
