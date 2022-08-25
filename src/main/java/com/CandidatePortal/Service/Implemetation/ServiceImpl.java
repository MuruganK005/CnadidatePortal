package com.CandidatePortal.Service.Implemetation;

import com.CandidatePortal.DTO.CandidateRegistrationDto;
import com.CandidatePortal.Entity.Candidate;
import com.CandidatePortal.Entity.VerificationToken;

import java.util.Optional;

public interface ServiceImpl {

    Candidate candidateSignUp(CandidateRegistrationDto registrationDto);

    void saveVerificatioToken(String token, Candidate candidate);

    String validateVerifiesToken(String token);

    VerificationToken generateVerificationToken(String oldToken);

    Candidate findUserByEmail(String email);

    void createPasswordResetTokenForCandidate(Candidate candidate, String token);

    String validatePasswordResetToken(String token);

    Optional<Candidate> getCandidateByPasswordResetToken(String token);

    void changePassword(Candidate candidate, String newPassword);

    boolean checkIfValidOldPassword(Candidate candidate, String oldPassword);
}