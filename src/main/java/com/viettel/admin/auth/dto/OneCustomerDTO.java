package com.viettel.admin.auth.dto;


import com.viettel.admin.core.enums.UserGender;

public interface OneCustomerDTO {
  public Long getId();

  public String getUserCode();

  public Integer getPersonInCharge();

  public String getImageUrl();

  public String getFullName();

  public String getEmail();

  public String getMobile();

  public String getUserName();

  public UserGender getGender();

  public String getDateOfBirth();

  public Long getCashAmount();

  public Integer getFeeRatio();

  public Long getRatesJpToVn();

  public String getAddress();
}
