package com.viettel.admin.auth.dto;

import com.viettel.admin.core.service.common.ConvertString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

  @NotNull
  @Convert(converter = ConvertString.class)
  private String fullName;

  @Convert(converter = ConvertString.class)
  private String email;
  @NotNull
  @Convert(converter = ConvertString.class)
  private String mobile;

//  private String role;

  @NotNull
  private String password;

  @NotNull
  private String passwordConfirm;

}
