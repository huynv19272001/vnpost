package com.viettel.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

  @NotNull
  private String username;

  @NotNull
  private String password;

}
