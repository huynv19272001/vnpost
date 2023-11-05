package com.viettel.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDto {

  @NotNull
  @NotBlank
  private String currentPassword;

  @NotNull
  @NotBlank
  private String newPassword;

  @NotNull
  @NotBlank
  private String confirmNewPassword;

}
