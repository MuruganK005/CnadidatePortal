package com.CandidatePortal.Event;

import com.CandidatePortal.Entity.Candidate;
import com.CandidatePortal.Service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class RegistrationCompleteListener implements ApplicationListener<RegistrationEvent> {
    @Autowired
    private CandidateService service;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        //create the verification token with the link
        Candidate candidate= event.getCandidate();
        String token= UUID.randomUUID().toString();
        service.saveVerificatioToken(token,candidate);
        //send mail to user
        String url=event.getAppsUrl()+"/api/v1/verifyRegistration?token="+token;

        log.info("Click the link to verify your Account:"+url);

    }
}
