package com.CandidatePortal.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UrlDto {
    private String url;
    private String expirationDate;  //optional
}
