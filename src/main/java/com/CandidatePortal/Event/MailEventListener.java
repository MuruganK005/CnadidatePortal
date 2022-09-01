package com.CandidatePortal.Event;

import com.CandidatePortal.MailSender.MailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@Slf4j
public class MailEventListener {
    @Autowired
    private MailSenderService service;

//    @EventListener(ApplicationReadyEvent.class)
//    public void triggerEmail() throws MessagingException {
//        service.sendMailWithAttachment("elavarasansiva123@gmail.com","This is Body","This is Subject",
//                "C:\\Users\\hi\\Pictures\\pic.jpg");
//        log.info("This is Mail Sender page");
//    }
}
