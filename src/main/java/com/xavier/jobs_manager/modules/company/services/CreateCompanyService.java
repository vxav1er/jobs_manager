package com.xavier.jobs_manager.modules.company.services;

import com.xavier.jobs_manager.exeptions.UserFoundException;
import com.xavier.jobs_manager.modules.company.entities.CompanyEntity;
import com.xavier.jobs_manager.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException();
        });

        return this.companyRepository.save(companyEntity);
    }
}
