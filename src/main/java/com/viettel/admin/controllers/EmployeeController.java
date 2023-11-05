package com.viettel.admin.controllers;

import com.viettel.admin.core.model.ResponseWrapper;
import com.viettel.admin.models.Employee;
import com.viettel.admin.models.Province;
import com.viettel.admin.services.EmployeeService;
import com.viettel.admin.services.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/employee/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("list")
    public ResponseEntity<?> getList() {
        try {
            List<Employee> employeeList =  employeeService.getList();
            return new ResponseEntity<>(new ResponseWrapper(employeeList), HttpStatus.OK );
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping("creat")
    public ResponseEntity<?> creat(@RequestBody Employee employee) {
        try {
            Employee result =  employeeService.create(employee);
            return new ResponseEntity<>(new ResponseWrapper(result), HttpStatus.OK );
        }catch (IllegalArgumentException ex){
            return new ResponseEntity<>(new ResponseWrapper(ex.getMessage(), null), HttpStatus.BAD_REQUEST );
        }
    }
}
