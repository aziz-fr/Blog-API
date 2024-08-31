package com.cogent.blogrestapi.controller;

import com.cogent.blogrestapi.payload.LoginDto;
import com.cogent.blogrestapi.payload.RegisterDto;
import com.cogent.blogrestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register (@RequestBody RegisterDto registerDto){
    String response = authService.register(registerDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login (@RequestBody LoginDto loginDto){
    String response = authService.login(loginDto);
    return ResponseEntity.ok(response);
  }

}
