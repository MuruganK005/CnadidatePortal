package com.CandidatePortal.MailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendMailWithAttachment(String toMail,String body,String subject,String attachment) throws MessagingException {
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);


        helper.setFrom("muruganeee005@gmail.com");
        helper.setTo(toMail);
        helper.setText(body);
        helper.setSubject(subject);

        FileSystemResource fileSystemResource=new FileSystemResource(new File(attachment));
        helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        mailSender.send(message);
        System.out.println("Mail has Send Successfully...");
    }
}
