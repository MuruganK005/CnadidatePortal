package com.CandidatePortal.Service;


import com.CandidatePortal.DTO.CandidateRegistrationDto;
import com.CandidatePortal.DTO.LoginDTO;
import com.CandidatePortal.Entity.Candidate;
import com.CandidatePortal.Entity.PasswordResetToken;
import com.CandidatePortal.Entity.VerificationToken;
import com.CandidatePortal.Exception.CandidateException;
import com.CandidatePortal.Model.Password;
import com.CandidatePortal.Repository.CandidateRepo;
import com.CandidatePortal.Repository.PasswordResetTokenRepo;
import com.CandidatePortal.Repository.VerificationTokenRepo;
import com.CandidatePortal.Service.Implemetation.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateService implements ServiceImpl {

    @Autowired
    private CandidateRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepo tokenRepo;

    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;


    @Override
    public Candidate candidateSignUp(CandidateRegistrationDto registrationDto) {
        Candidate  candidate=new Candidate();
        candidate.setFirstName(registrationDto.getFirstName());
        candidate.setLastName(registrationDto.getLastName());
        candidate.setDob(registrationDto.getDob());
        candidate.setExperience(registrationDto.getExperience());
        candidate.setSkills(registrationDto.getSkills());
        candidate.setRole("Candidate");
        candidate.setEmail(registrationDto.getEmail());
        candidate.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        repo.save(candidate);
        return candidate;
    }

    @Override
    public void saveVerificatioToken(String token, Candidate candidate) {
        VerificationToken verificationToken=new VerificationToken(candidate, token);

        tokenRepo.save(verificationToken);
    }

    @Override
    public String validateVerifiesToken(String token) {
        VerificationToken verificationToken=tokenRepo.findByToken(token);
        if (verificationToken==null){
            return "invalid";
        }
        Candidate candidate=verificationToken.getCandidate();
        Calendar cal=Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime()-cal.getTime().getTime()<=0){
            tokenRepo.delete(verificationToken);
            return "expired";
        }
        candidate.setEnabled(true);
        repo.save(candidate);
        return "valid";
    }

    @Override
    public VerificationToken generateVerificationToken(String oldToken) {
        VerificationToken verificationToken=tokenRepo.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        tokenRepo.save(verificationToken);
        return verificationToken;
    }

    @Override
    public Candidate findUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForCandidate(Candidate candidate, String token) {
        PasswordResetToken passwordResetToken=new PasswordResetToken(candidate,token);
        passwordResetTokenRepo.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken=passwordResetTokenRepo.findByToken(token);
        if (passwordResetToken==null){
            return "invalid";
        }
        Candidate candidate=passwordResetToken.getCandidate();
        Calendar cal=Calendar.getInstance();
        if (passwordResetToken.getExpirationTime().getTime()-cal.getTime().getTime()<=0){
            passwordResetTokenRepo.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }

    @Override
    public Optional<Candidate> getCandidateByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepo.findByToken(token).getCandidate());
    }

    @Override
    public void changePassword(Candidate candidate, String newPassword) {
        candidate.setPassword(passwordEncoder.encode(newPassword));
        repo.save(candidate);
    }

    @Override
    public boolean checkIfValidOldPassword(Candidate candidate, String oldPassword) {
        return passwordEncoder.matches(oldPassword,candidate.getPassword());
    }

    @Override
    public Candidate login(LoginDTO loginDTO) throws CandidateException {
        Candidate candidate = repo.findByEmail(loginDTO.getEmail());
        if (candidate !=null) {
            boolean ismatch = passwordEncoder.matches(loginDTO.getPassword(), candidate.getPassword());
            if (ismatch) {
                return candidate;
            } else {
                throw new CandidateException(HttpStatus.BAD_REQUEST,"Invalid Credential");
            }
        } else {
            throw new CandidateException(HttpStatus.BAD_REQUEST,"Invalid Credential");
        }
    }
}


