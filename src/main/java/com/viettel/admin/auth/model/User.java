package com.viettel.admin.auth.model;

import com.viettel.admin.core.enums.UserGender;
import com.viettel.admin.core.enums.UserStatus;
import com.viettel.admin.core.model.BaseEntity;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

  private String title;

  private String managementUnit;

  private String password;

  private String email;

  private String fullName;

  private String mobile;

  @Column(name = "user_code")
  private String userCode;

  private String userId;





//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return roles.stream()
//            .map(author -> new SimpleGrantedAuthority(author.getRoleName()))
//            .collect(Collectors.toList());
//  }

}
