package com.cogent.blogrestapi.service;

import com.cogent.blogrestapi.payload.LoginDto;
import com.cogent.blogrestapi.payload.RegisterDto;

public interface AuthService {
  String login(LoginDto loginDto);
  String register(RegisterDto registerDto);
}
