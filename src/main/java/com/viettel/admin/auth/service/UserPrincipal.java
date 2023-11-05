package com.viettel.admin.auth.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.viettel.admin.auth.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class UserPrincipal implements UserDetails {
  private static final long serialVersionUID = 1L;

  private final Long userId;

  private String username;

  private String email;

  private String title;

  private String managementUtil;

  private String mobi;

  private String userCode;

  @JsonIgnore
  private String password;


  private final Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
  }

  public UserPrincipal(Long userId, String username, String email, String title,
                       String managementUtil, String mobi, String password,String userCode, Collection<? extends GrantedAuthority> authorities) {
    this.userId = userId;
    this.username = username;
    this.email = email;
    this.title = title;
    this.managementUtil = managementUtil;
    this.mobi = mobi;
    this.password = password;
    this.userCode = userCode;
    this.authorities = authorities;
  }


  public static UserPrincipal build(User user, Set<GrantedAuthority> authorities) {

    return new UserPrincipal(
            user.getId(),
            user.getFullName(),
            user.getEmail(),
            user.getTitle(),
            user.getManagementUnit(),
            user.getMobile(),
            user.getPassword(),
            user.getUserCode(),
            authorities);
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return userId;
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserPrincipal user = (UserPrincipal) o;
    return Objects.equals(userId, user.userId);
  }


  public static UserPrincipal getAuthorizedUser() {
    return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
