package com.hotel.service.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendNotification(String notificationMsg) throws MessagingException, UnsupportedEncodingException {


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("saurav.kumar3279@gmail.com", "Hotel Service");
        helper.setTo("saukr678@gmail.com");

        String content = "<p>Hi, please check the hotel ratings below: </b></p><br>"
                + "<p>" + notificationMsg + "</p>";

        helper.setSubject("Hotel rating received !");
        helper.setText(content, true);
        mailSender.send(message);
    }
}
