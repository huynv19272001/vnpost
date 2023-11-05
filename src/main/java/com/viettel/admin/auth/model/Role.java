package com.viettel.admin.auth.model;

import com.viettel.admin.core.model.BaseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    private String roleName;

    private String roleKey;

    private Integer status;

    private String roleId;

    public Role() {
    }

    public Role(String roleKey) {
        this.roleKey = roleKey;
    }

    public Role(String roleKey, String roleName) {
        this.roleKey = roleKey;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    @Override
    public String getAuthority() {
        return roleKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
