package com.xavier.jobs_manager.modules.company.repositories;

import com.xavier.jobs_manager.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
