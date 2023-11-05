package com.viettel.admin.repositories;

import com.viettel.admin.models.Department;
import com.viettel.admin.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


}