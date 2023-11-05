package com.viettel.admin.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSearch {
  private String query;

  private Integer status;

  private Integer personInCharge;

  private Integer feeStatus;

  private String sortBy;

  private String sortDirection;

  private Integer pageNumber;

  private Integer pageSize;
}
