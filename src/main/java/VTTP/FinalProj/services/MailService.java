package VTTP.FinalProj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String receipient, String subject, String body ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receipient);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("weishunlim96@gmail.com");

        mailSender.send(message);
        System.out.println("SENT MESSAGE!!!");
    }
}
