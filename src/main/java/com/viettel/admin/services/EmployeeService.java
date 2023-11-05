package com.viettel.admin.services;

import com.viettel.admin.models.Employee;
import com.viettel.admin.models.Province;

import java.util.List;


public interface EmployeeService {

    List<Employee> getList();

    Employee create(Employee employee);
}
