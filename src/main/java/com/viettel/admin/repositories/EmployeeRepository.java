package com.viettel.admin.repositories;

import com.viettel.admin.models.Employee;
import com.viettel.admin.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}