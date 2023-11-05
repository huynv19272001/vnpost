package com.viettel.admin.auth.dto;

import lombok.Data;

@Data
public class EmployeeSearch {
  private String query;

  private Integer status;

  private Integer personInCharge;

  private Long role;

  private String sortBy;

  private String sortDirection;

  private Integer pageNumber;

  private Integer pageSize;
}
