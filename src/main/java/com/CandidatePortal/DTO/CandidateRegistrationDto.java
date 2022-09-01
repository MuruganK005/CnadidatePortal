package com.CandidatePortal.DTO;

import com.CandidatePortal.Entity.Skill;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CandidateRegistrationDto {

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Integer experience;
    private List<Skill> skills =new ArrayList<>();
    private String email;
    private String password;
    private String role;
    private boolean enabled=false;
    private String location;

}
