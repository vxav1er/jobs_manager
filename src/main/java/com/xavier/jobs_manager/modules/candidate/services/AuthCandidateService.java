package com.xavier.jobs_manager.modules.candidate.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xavier.jobs_manager.exeptions.IncorrectPasswordException;
import com.xavier.jobs_manager.modules.candidate.CandidateRepository;
import com.xavier.jobs_manager.modules.candidate.dto.AuthCandidateRequestDTO;
import com.xavier.jobs_manager.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateService {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) {
    var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Username/Password incorrect");
    });

    var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) {
      throw new IncorrectPasswordException("Username/password incorrect");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

    var token = JWT.create().withIssuer("jobs_manager").withExpiresAt(expiresIn)
        .withSubject(candidate.getId().toString()).withClaim("roles", Arrays.asList("candidate")).sign(algorithm);

    var authCadidateResponse = AuthCandidateResponseDTO.builder().access_token(token)
        .expires_in(expiresIn.toEpochMilli()).build();

    return authCadidateResponse;

  }
}
