package com.xavier.jobs_manager.modules.company.controllers;

import com.xavier.jobs_manager.modules.company.dto.CreateJobDTO;
import com.xavier.jobs_manager.modules.company.entities.JobEntity;
import com.xavier.jobs_manager.modules.company.services.CreateJobService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobService createJobService;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder().companyId(UUID.fromString(companyId.toString()))
                .benefits(createJobDTO.getBenefits())
                .description(createJobDTO.getDescription()).level(createJobDTO.getLevel()).build();

        return this.createJobService.execute(jobEntity);
    }
}
