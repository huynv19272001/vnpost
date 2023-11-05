package com.viettel.admin.auth.model;

import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "role_id")
  private Long roleId;

}
