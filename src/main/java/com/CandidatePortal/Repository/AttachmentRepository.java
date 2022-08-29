package com.CandidatePortal.Repository;

import com.CandidatePortal.Entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
    Optional<Object> findById(String fileId);
}
