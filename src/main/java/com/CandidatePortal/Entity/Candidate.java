package com.CandidatePortal.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "candidate")
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "d_o_b")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;
    @Column(name = "experience")
    private Integer experience;
    @Column(name="password",length = 60)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "enabled")
    private boolean enabled=false;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Skill> skills =new ArrayList<>();

}
