package com.CandidatePortal.Controller;

import com.CandidatePortal.Entity.Candidate;
import com.CandidatePortal.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private CandidateService service;

    @GetMapping("/search")
    public List<Candidate> searchNamesBased(@RequestParam(name = "search") String search){
        return service.searchByCandidateNamesAndExp(search);
    }
}
