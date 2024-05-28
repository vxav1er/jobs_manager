package com.xavier.jobs_manager.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.jobs_manager.exeptions.IncorrectPasswordException;
import com.xavier.jobs_manager.modules.candidate.dto.AuthCandidateRequestDTO;
import com.xavier.jobs_manager.modules.candidate.services.AuthCandidateService;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateService authCandidateService;

  @PostMapping("/candidate")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
    try {
      var result = this.authCandidateService.execute(authCandidateRequestDTO);
      return ResponseEntity.ok().body(result);
    } catch (UsernameNotFoundException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } catch (IncorrectPasswordException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }

  }
}
