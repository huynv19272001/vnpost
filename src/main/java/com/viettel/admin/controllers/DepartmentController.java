package com.viettel.admin.controllers;

import com.viettel.admin.auth.model.User;
import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Department;
import com.viettel.admin.models.Organization;
import com.viettel.admin.services.DepartmentService;
import com.viettel.admin.services.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/department/")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        try{
            List<Department> departments =  departmentService.getList();
            return new ResponseEntity<>(new ResponseWrapper(departments),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Department request) {
        try {
            Department department =  departmentService.create(request);
            return new ResponseEntity<>(new ResponseWrapper(department),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null),HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Department request) {
        try {
            Department department =  departmentService.update(request);
            return new ResponseEntity<>(new ResponseWrapper(department),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),request),HttpStatus.BAD_REQUEST );
        }

    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        try {
            Boolean bl =  departmentService.delete(id);
            return new ResponseEntity<>(new ResponseWrapper(bl),HttpStatus.OK );
        }catch (Exception ex){
            return new ResponseEntity<>(new ResponseWrapper( ex.getMessage(),false),HttpStatus.BAD_REQUEST );
        }

    }
}
