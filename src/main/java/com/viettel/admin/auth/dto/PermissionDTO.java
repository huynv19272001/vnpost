package com.viettel.admin.auth.dto;

import lombok.Data;

@Data
public class PermissionDTO {
  private Long id;

  private String permissionName;

  private String permissionKey;
}
