package com.viettel.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

  @NotNull
  @NotBlank
  private String confirmPassword;

  @NotNull
  @NotBlank
  private String newPassword;

}
