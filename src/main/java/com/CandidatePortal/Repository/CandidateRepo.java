package com.CandidatePortal.Repository;

import com.CandidatePortal.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Long> {

    Candidate findByEmail(String email);
    @Query("SELECT p FROM Candidate p JOIN p.skills r WHERE CAST(p.experience AS string) LIKE %:search% AND r.skillName LIKE %:search1%")
    List<Candidate> findByFirstnameAndExperience(String search, String search1);
}
