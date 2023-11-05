package com.viettel.admin.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewPasswordDTO {
  @NotNull
  private String newPassword;
  @NotNull
  private String renewPassword;
  @NotNull
  private String email;
  @NotNull
  private String otp;
}
