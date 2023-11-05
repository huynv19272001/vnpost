package com.viettel.admin.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateEmployeeReq {
  private Long id;
  private String fullName;
  private List<Long> roleIds;
  private String mobile;
  private String email;
  private Integer status;
  private Long salary;
  private Long score;
}
