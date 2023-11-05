package com.viettel.admin.auth.dto;

import com.viettel.admin.core.enums.UserGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateByAdmin {
  private String fullName;

  private Integer personInCharge;

  private String mobile;

  private String email;

  private UserGender gender;

  private String dateOfBirth;

  private Integer feeRatio;

  private Long ratesJpToVn;
}
