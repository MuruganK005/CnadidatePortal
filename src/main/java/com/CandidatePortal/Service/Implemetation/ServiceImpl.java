package com.CandidatePortal.Service.Implemetation;

import com.CandidatePortal.DTO.CandidateRegistrationDto;
import com.CandidatePortal.DTO.LoginDTO;
import com.CandidatePortal.Entity.Candidate;
import com.CandidatePortal.Entity.VerificationToken;
import com.CandidatePortal.Exception.CandidateException;

import java.util.List;
import java.util.Optional;

public interface ServiceImpl {

    Candidate candidateSignUp(CandidateRegistrationDto registrationDto);

    void saveVerificatioToken(String token, Candidate candidate);

    String validateVerifiesToken(String token);

    VerificationToken generateVerificationToken(String oldToken);

    Candidate findUserByEmail(String email);

    void createPasswordResetTokenForCandidate(Candidate candidate, String token);

    String validatePasswordResetToken(String token);

    Candidate login(LoginDTO loginDTO) throws CandidateException;

    Optional<Candidate> getCandidateByPasswordResetToken(String token);

    void changePassword(Candidate candidate, String newPassword);

    boolean checkIfValidOldPassword(Candidate candidate, String oldPassword);

    List<Candidate> searchByCandidateNamesAndExp(String search);

    List<Candidate> searchByLocation(String search);

    List<Candidate> getAllCandidate();

    void deleteCandidates(Long id);
}
