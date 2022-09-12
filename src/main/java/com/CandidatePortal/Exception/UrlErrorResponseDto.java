package com.CandidatePortal.Exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UrlErrorResponseDto {
    private String status;
    private String error;
}
