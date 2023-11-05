package com.viettel.admin.auth.dto.request;


import com.viettel.admin.auth.model.Role;
import com.viettel.admin.auth.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser {
    private User user;
    private List<Role> roles;

}