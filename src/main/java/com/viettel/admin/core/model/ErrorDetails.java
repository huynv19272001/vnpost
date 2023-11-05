package com.viettel.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ErrorDetails {

  private static final String defaultStatus = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
  private static final String defaultMessage = "Failed";

  @Builder.Default
  private Date timestamp = new Date();

  @Builder.Default
  private String httpStatusCode = defaultStatus;

  @Builder.Default
  private String messageCode = defaultMessage;

  private Object details;

  private String path;

}
