package com.cogent.blogrestapi.service.impl;

import com.cogent.blogrestapi.entity.Role;
import com.cogent.blogrestapi.entity.User;
import com.cogent.blogrestapi.exception.BlogApiException;
import com.cogent.blogrestapi.payload.LoginDto;
import com.cogent.blogrestapi.payload.RegisterDto;
import com.cogent.blogrestapi.repository.RoleRepository;
import com.cogent.blogrestapi.repository.UserRepository;
import com.cogent.blogrestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
@Autowired
private AuthenticationManager authenticationManager;
  @Override
  public String login(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            // returns authentication obj
            loginDto.getUsernameOrEmail(), loginDto.getPassword()
    ));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    return "login successful";
  }

  @Override
  public String register(RegisterDto registerDto) {
    // add check for username exists in database
    if (userRepository.existsByUsername(registerDto.getUsername())) {
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "username already exists");
    }

    // add a check for email exist in database
    if (userRepository.existsByEmail(registerDto.getEmail())) {
      throw new BlogApiException(HttpStatus.BAD_REQUEST, "email already exists");
    }

    User user = new User();
    user.setName(registerDto.getName());
    user.setUsername(registerDto.getUsername());
    user.setEmail(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    // assign a role to the user
    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName("admin").get();
    roles.add(userRole);
    user.setRoles(roles);

    // save the user into database
    userRepository.save(user);
    return "User registered successfully!";
  }
}
