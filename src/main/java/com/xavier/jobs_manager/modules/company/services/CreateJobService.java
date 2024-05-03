package com.xavier.jobs_manager.modules.company.services;

import com.xavier.jobs_manager.modules.company.entities.JobEntity;
import com.xavier.jobs_manager.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
