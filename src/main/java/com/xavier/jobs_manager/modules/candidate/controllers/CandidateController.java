package com.xavier.jobs_manager.modules.candidate.controllers;


import com.xavier.jobs_manager.modules.candidate.CandidateEntity;
import com.xavier.jobs_manager.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;


    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity) {
        return this.candidateRepository.save(candidateEntity);
    }
}