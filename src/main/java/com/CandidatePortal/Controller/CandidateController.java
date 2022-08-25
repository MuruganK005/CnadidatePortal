package com.CandidatePortal.Controller;

import com.CandidatePortal.DTO.CandidateRegistrationDto;
import com.CandidatePortal.Entity.Candidate;
import com.CandidatePortal.Model.Password;
import com.CandidatePortal.Entity.VerificationToken;
import com.CandidatePortal.Event.RegistrationEvent;
import com.CandidatePortal.Service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/sign-up")
    public String  candidateSignUp(@RequestBody CandidateRegistrationDto registrationDto, final HttpServletRequest request) {
        Candidate candidate= service.candidateSignUp(registrationDto);
        publisher.publishEvent(new RegistrationEvent(candidate,applicationUrl(request)));
        return "Sign up Successfully Done!! ";
    }
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam String token){
        String result=service.validateVerifiesToken(token);
        if (result.equals("valid")){
            return "Candidate Verifies SuccessFully";
        }
        return "Bad Credential";
    }
    @GetMapping("/resendVerificationToken")
    public String resendVerificationToken(@RequestParam(name = "token") String oldToken, HttpServletRequest http){
        VerificationToken verificationToken=service.generateVerificationToken(oldToken);
        Candidate candidate= verificationToken.getCandidate();
        resendVerificationTokentoMail(candidate, applicationUrl(http),verificationToken);
        return "verification Link sent";
    }
    @PostMapping("/passwordReset")
    public String resetPassword(@RequestBody Password password,HttpServletRequest request){
    Candidate candidate=service.findUserByEmail(password.getEmail());
    String url="";
    if(candidate!=null){
        String token= UUID.randomUUID().toString();
        service.createPasswordResetTokenForCandidate(candidate,token);
        url=passwordResetTokenMail(candidate,applicationUrl(request),token);
    }
    return url;
    }
    @PostMapping("/savePassword")
    public String savePassword(@RequestParam(name = "token") String token,@RequestBody Password password){
    String result =service.validatePasswordResetToken(token);
    if (!result.equals("valid")){
        return "Invalid Token";
    }
        Optional<Candidate> candidate=service.getCandidateByPasswordResetToken(token);
        if (candidate.isPresent()){
            service.changePassword(candidate.get(),password.getNewPassword());
            return "Password Reset SuccessFully";
        }else{
            return "invalid token";
        }
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestBody Password password){
        Candidate candidate=service.findUserByEmail(password.getEmail());
        if (!service.checkIfValidOldPassword(candidate,password.getOldPassword())){
            return "Invalid OldPassword";
        }
        //save new Password
        service.changePassword(candidate,password.getNewPassword());
        return "Password changed Successfully";
    }

    private String passwordResetTokenMail(Candidate candidate, String applicationUrl,String token) {
        String url=applicationUrl+"/api/v1/savePassword?token="+token;
        log.info("Click the link to verify your Account:"+url);
        return url;
    }

    private void resendVerificationTokentoMail(Candidate candidate, String applicationUrl, VerificationToken verificationToken) {
        String url=applicationUrl+"/api/v1/verifyRegistration?token="+verificationToken.getToken();

        log.info("Click the link to verify your Account:"+url);
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }
}
