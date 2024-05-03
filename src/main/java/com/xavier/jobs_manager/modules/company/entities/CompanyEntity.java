package com.xavier.jobs_manager.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "companies")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String companyRegistry;
    private CompanyRegistryType companyRegistryType;
    @NotBlank
    @Pattern(regexp = "\\S+", message = "The [username] field must not contain a space")
    private String username;

    @Email(message = "The [email] field must contain a valid email address.")
    private String email;

    @Length(min = 10, message = "The [password] field must have at least 10 characters.")
    @Length(max = 100, message = "The [password] field must have a maximum of 100 characters.")
    private String password;
    private String website;
    private String description;
    private Region region;

    @CreationTimestamp
    private LocalDateTime createTime;
}
