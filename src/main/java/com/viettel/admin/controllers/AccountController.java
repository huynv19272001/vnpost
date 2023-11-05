package com.viettel.admin.controllers;

import com.viettel.admin.auth.dto.ChangePasswordDto;
import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Bank;
import com.viettel.admin.services.AccountService;
import com.viettel.admin.services.BankService;
import com.viettel.admin.services.excel.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/user")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ExcelService excelService;

    @PostMapping("import")
    public ResponseEntity<?> importExcel(@RequestParam("file") MultipartFile file) {
        try{
            excelService.importExcel(file);
            return new ResponseEntity<>(new ResponseWrapper(null, "Import file successfully"),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping(value = "change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto) {

        try{
            accountService.changePassword(changePasswordDto);
            return new ResponseEntity<>(new ResponseWrapper(null, "Change password successfully"),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping(value = "reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid String mobile) {

        try{
            accountService.resetPassword(mobile);
            return new ResponseEntity<>(new ResponseWrapper(null, "Reset password successfully"),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }
}
