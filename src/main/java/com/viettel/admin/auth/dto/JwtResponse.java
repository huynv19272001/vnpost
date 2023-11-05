package com.viettel.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private String refreshToken;
  private String notification;
  private String role;
  private Long expiresIn;
  private Long refreshExpiresIn;

  public JwtResponse(String accessToken, String refreshToken, Long expiresIn, Long refreshExpiresIn, String notification, String role) {
    this.token = accessToken;
    this.refreshToken = refreshToken;
    this.notification = notification;
    this.role = role;
    this.expiresIn = expiresIn;
    this.refreshExpiresIn = refreshExpiresIn;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getNotification() {
    return notification;
  }

  public void setNotification(String notification) {
    this.notification = notification;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public Long getRefreshExpiresIn() {
    return refreshExpiresIn;
  }

  public void setRefreshExpiresIn(Long refreshExpiresIn) {
    this.refreshExpiresIn = refreshExpiresIn;
  }
}

