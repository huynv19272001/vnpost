package com.viettel.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OtpVerify {
  private String otp;
  private Integer retryCount;

}
