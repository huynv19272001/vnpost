package com.viettel.admin.services.impl;

import com.viettel.admin.auth.dto.ChangePasswordDto;
import com.viettel.admin.auth.model.User;
import com.viettel.admin.auth.service.UserPrincipal;
import com.viettel.admin.common.Const;
import com.viettel.admin.repositories.AccountRepository;
import com.viettel.admin.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void changePassword(ChangePasswordDto changePasswordDto){
        UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(user, Const.MESSAGE_CODE.USER_NOT_FOUND);
        User userChange = accountRepository.findByMobile(user.getMobi()).orElse(null);
        Assert.notNull(userChange, Const.MESSAGE_CODE.USER_NOT_FOUND);
        userChange.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        accountRepository.save(userChange);
    }

    public void resetPassword(String mobile){
        User user = accountRepository.findByMobile(mobile).orElse(null);
        Assert.notNull(user, Const.MESSAGE_CODE.USER_NOT_FOUND);
        user.setPassword(passwordEncoder.encode("123456"));
        accountRepository.save(user);
    }
}
