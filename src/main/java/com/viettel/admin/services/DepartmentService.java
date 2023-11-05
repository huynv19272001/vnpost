package com.viettel.admin.services;

import com.viettel.admin.models.Department;
import com.viettel.admin.models.Organization;

import java.util.List;


public interface DepartmentService {

    List<Department> getList();

    Department create(Department request);

    Department update(Department request);

    Boolean delete(Long id);

}
