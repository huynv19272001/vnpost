package com.viettel.admin.auth.dto;

import com.viettel.admin.auth.model.Role;
import com.viettel.admin.core.enums.UserGender;
import com.viettel.admin.core.enums.UserStatus;
import com.viettel.admin.core.service.common.ConvertString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;

  @Convert(converter = ConvertString.class)
  private String userCode;

  private Integer personInCharge;

  private String imageUrl;
  @Convert(converter = ConvertString.class)
  private String fullName;
  @Convert(converter = ConvertString.class)
  private String email;
  @Convert(converter = ConvertString.class)
  private String mobile;
  @Convert(converter = ConvertString.class)
  private String username;

  private UserGender gender;

  private String dateOfBirth;

  private UserStatus status;

  private Set<Role> roles = new HashSet<>();

  private Date createdAt;

  private Date updatedAt;

  private String createdBy;

  private String updatedBy;

  private Long salary;

  private Long score;

  private Long cashAmount;

  private Integer userStatus;

  public Integer getUserStatus() {
    return status.getValue();
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }
}
