package com.CandidatePortal.Service.Implemetation;

import com.CandidatePortal.DTO.UrlDto;
import com.CandidatePortal.Entity.Url;

public interface UrlImpl {
    public Url generateShortLink(UrlDto urlDto);
    public Url persistShortLink(Url url);
    public Url getEncodedUrl(String url);
    public  void  deleteShortLink(Url url);
}
