package com.cogent.blogrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
// data obj
public class RegisterDto {
  private String name;
  private String username;
  private String email;
  private String password;


}
