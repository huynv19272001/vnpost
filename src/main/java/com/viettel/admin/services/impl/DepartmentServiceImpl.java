package com.viettel.admin.services.impl;

import com.viettel.admin.models.Department;
import com.viettel.admin.models.Organization;
import com.viettel.admin.repositories.DepartmentRepository;
import com.viettel.admin.repositories.OrganizationRepository;
import com.viettel.admin.services.DepartmentService;
import com.viettel.admin.services.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getList() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public Department create(Department request) {
        Department department = new Department();
        BeanUtils.copyProperties(request, department);
        departmentRepository.save(department);
        return request;
    }

    @Override
    public Department update(Department request) {
        Department department = departmentRepository.getById(request.getId());
        department.setDepartmentCode(request.getDepartmentCode());
        department.setDepartmentName(request.getDepartmentName());
        department.setAddress(request.getAddress());
        department.setEmail(request.getEmail());
        department.setFax(request.getFax());
        department.setPrincipleOfOperation(request.getPrincipleOfOperation());
        department.setDateOfIncorporation(request.getDateOfIncorporation());
        department.setFunctionDescription(request.getFunctionDescription());
        department.setFunctionsDutiesStaff(request.getFunctionsDutiesStaff());
        department.setImplementationOrganization(request.getImplementationOrganization());
        department.setMobile(request.getMobile());
        department.setOrganizeApparatus(request.getOrganizeApparatus());
        department.setPermissions(request.getPermissions());
        department.setPersonRepresentative(request.getPersonRepresentative());
        department.setTaskDescription(request.getTaskDescription());
        department.setOrganizationId(request.getOrganizationId());
        departmentRepository.save(department);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        departmentRepository.deleteById(id);
        return true;
    }
}
