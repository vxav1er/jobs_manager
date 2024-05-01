package com.xavier.jobs_manager.modules.candidate;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;
    @Pattern(regexp = "\\s+", message = "The [username] field must not contain a space")
    private String username;
    @Email(message = "The [email] field must contain a valid email address.")
    private String email;

    @Length(min = 10, message = "The [password] field must have at least 10 characters.")
    @Length(max = 100, message = "The [password] field must have a maximum of 100 characters.")
    private String password;
    private String description;
    private String curriculum;
}