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
public class PasswordResetToken {
    private static final int EXPIRATION_TIME=10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    public PasswordResetToken(Candidate candidate,String token){
        super();
        this.token=token;
        this.candidate=candidate;
        this.expirationTime=calculateExpirationTime(EXPIRATION_TIME);
    }
    public PasswordResetToken(String token){
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
