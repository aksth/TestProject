package com.test.thread;

import javax.mail.Session;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by intern1 on 5/21/2017.
 */
public class SendMail {

    final String USERNAME = "shresthaakash1993@gmail.com";
    final String PASSWORD = "";

    private Integer updateNo;

    public SendMail(Integer updateNo) {
        this.updateNo = updateNo;
    }

    public SendMail() {
    }

    public void send() {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("shresthaakash1993@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shrestha.akash01@gmail.com"));
            message.setSubject("Update No. " + updateNo);
            message.setText("Dear user, this is update no. " + updateNo);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
