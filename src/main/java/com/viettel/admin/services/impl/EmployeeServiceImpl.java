package com.viettel.admin.services.impl;

import com.viettel.admin.models.Employee;
import com.viettel.admin.models.Province;
import com.viettel.admin.repositories.EmployeeRepository;
import com.viettel.admin.repositories.ProvinceRepository;
import com.viettel.admin.services.EmployeeService;
import com.viettel.admin.services.ProvinceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee create(Employee employee) {
        Employee employeeSave = new Employee();
        BeanUtils.copyProperties(employee, employeeSave);
        employeeRepository.save(employeeSave);
        return employeeSave;
    }
}
