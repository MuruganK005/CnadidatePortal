package com.CandidatePortal.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
    private static final int EXPIRATION_TIME=10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public VerificationToken(Candidate candidate,String token){
        super();
        this.token=token;
        this.candidate=candidate;
        this.expirationTime=calculateExpirationTime(EXPIRATION_TIME);
    }
    public VerificationToken(String token){
        super();
        this.token=token;
        this.expirationTime=calculateExpirationTime(EXPIRATION_TIME);
    }
    private Date calculateExpirationTime(int expirationTime){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());

    }


}
