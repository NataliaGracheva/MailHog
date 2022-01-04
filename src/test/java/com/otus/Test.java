package com.otus;

import com.otus.ssl.SSL_sender;
import com.otus.ssl.SSL_sender2;
import com.otus.tls.TLS_sender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class Test {
//    private TLS_sender tlsSender = new TLS_sender("gracheva.na.2209@gmail.com", "Google2209");
//    private SSL_sender sslSender = new SSL_sender("gracheva.na.2209@gmail.com", "Google2209");
//    private SSL_sender2 sslSender2 = new SSL_sender2("gracheva.na.2209@gmail.com", "Google2209");
//    private Mailtrap_sender mailtrapSender = new Mailtrap_sender("gracheva.na.2209@gmail.com", "Google2209");

    @org.junit.jupiter.api.Test
    void sendMail() {
//        tlsSender.send("This is Subject", "TLS: This is text!", "support@devcolibri.com", "gracheva.na.2209@gmail.com");
//        sslSender.send("This is Subject", "SSL: This is text!", "support@devcolibri.com", "gracheva.na.2209@gmail.com");
//        sslSender2.send("This is Subject", "SSL: This is text!", "support@devcolibri.com", "gracheva.na.2209@gmail.com");
//        mailtrapSender.send("This is Subject", "This is text!", "support@devcolibri.com", "gracheva.na.2209@gmail.com");


        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "0.0.0.0");
        props.put("mail.smtp.port", "1025");

        String htmlContent = "<html> <head> <title></title> </head> <body> "
                + "<table align=\"left\"> <tbody> <tr> <td>"
                + " <a href=\"https://www.findbestopensource.com/images/findbestopensource-logo.jpg\""
                + " target=\"_blank\""
                + " rel=\"noopener\"><img src=\"https://hozyain.by/wp-content/uploads/2018/10/porosenok-825x549.jpg\" width=\"300\""
                + " height=\"auto\" style=\"display:block\"/></a></td> </tr> <tr><H1> This is a test letter </H1></tr> </tbody> </table></body></html>";

        Session mailSession = Session.getInstance(props, null);
        try {
            MimeMessage msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress("from@example.com"));
            msg.setRecipients(Message.RecipientType.TO, "to@example.com");
            msg.setSubject("This is Subject");
            msg.setSentDate(new Date());

            MimeMultipart content = new MimeMultipart();
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(htmlContent, "text/html; charset=UTF-8");
            //bodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");

            BodyPart bodyPart1 = new MimeBodyPart();
            bodyPart1.setContent("This is a test letter", "text/plain; charset=ISO-8859-1");

            content.addBodyPart(bodyPart);
            content.addBodyPart(bodyPart1);
            msg.setContent(content);
            Transport.send(msg);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
