package com.viettel.admin.auth.controller;

import com.viettel.admin.auth.service.UserService;
import com.viettel.admin.payload.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/user", "/users"})
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/filter"})
    public ResponseEntity<?> filter(
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "orderBy", defaultValue = "createAt") String orderBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc) {
        return ResponseObject.success(userService.filterUser(userName, email, phoneNumber, page, size, orderBy, desc));
    }
}
