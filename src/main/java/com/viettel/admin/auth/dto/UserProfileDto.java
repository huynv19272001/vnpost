package com.viettel.admin.auth.dto;

import com.viettel.admin.core.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

  private Long id;

  private String userCode;

  private String fullName;

  private String email;

  private String mobile;

  private String username;

  private UserStatus status;

  private Object personInCharge;

  private Date createdAt;

  private Date updatedAt;

  private String createdBy;

  private String updatedBy;

}
