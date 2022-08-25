package com.CandidatePortal.Event;

import com.CandidatePortal.Entity.Candidate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {
    private Candidate candidate;
    private String AppsUrl;
    public RegistrationEvent(Candidate candidate,String AppsUrl) {
        super(candidate);
        this.candidate=candidate;
        this.AppsUrl=AppsUrl;
    }
}
