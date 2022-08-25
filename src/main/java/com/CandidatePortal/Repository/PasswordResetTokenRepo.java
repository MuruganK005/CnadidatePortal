package com.CandidatePortal.Repository;

import com.CandidatePortal.Entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken,Long> {
    PasswordResetToken findByToken(String token);
}
