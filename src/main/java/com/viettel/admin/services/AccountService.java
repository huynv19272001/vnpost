package com.viettel.admin.services;

import com.viettel.admin.auth.dto.ChangePasswordDto;
import com.viettel.admin.models.Bank;

import java.util.List;


public interface AccountService {

    void changePassword(ChangePasswordDto changePasswordDto);

    void resetPassword(String mobile);
}
