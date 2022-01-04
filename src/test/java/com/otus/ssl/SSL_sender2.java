package com.otus.ssl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SSL_sender2 {

    private String username;
    private String password;
    private Properties props;

    //
    private String smtpMailProvider;
    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public SSL_sender2(String username, String password) {
        this.username = username;
        this.password = password;

//        props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");// указываем, что будем использовать для отправки протокол SSL
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");// ?!

        if (username.substring(username.indexOf("@") + 1, username.length()).equals("hotmail.com")) {
            this.smtpMailProvider = "smtp.live.com";
        } else this.smtpMailProvider = "smtp." + username.substring(username.indexOf("@") + 1, username.length());

        props = System.getProperties();

        props.put("mail.smtp.user", username);
        props.put("mail.smtp.host", smtpMailProvider);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.ssl.trust", smtpMailProvider);

        //
        props.put("mail.transport.protocol", "smtp");

    }

    public void send(String subject, String text, String fromEmail, String toEmail){
//        Session session = Session.getDefaultInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });

        Session thisSession = Session.getInstance(props, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SSL_sender2.this.username, SSL_sender2.this.password);
            }
        });

        //
        Message message = new MimeMessage(thisSession);

        try {
//            Message message = new MimeMessage(session);
            //от кого
            message.setFrom(new InternetAddress(username));
            //кому
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //тема сообщения
            message.setSubject(subject);
            //текст
            message.setText(text);

            //отправляем сообщение
            Transport.send(message);
//            Transport transport = thisSession.getTransport();
//            transport.connect(smtpMailProvider, username, password);
//            Transport.send(message);
            System.out.println("Your e-mail has been sent.");
        } catch (MessagingException e) {
//            throw new RuntimeException(e);
            System.out.println("Uuups... something went wrong...");
            System.out.println(e.getMessage());
            System.exit(2);
        }
    }
}
