package com.CandidatePortal.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UrlResponseData {
    private String originalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;
}
