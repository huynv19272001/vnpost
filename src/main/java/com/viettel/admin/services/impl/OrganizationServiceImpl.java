package com.viettel.admin.services.impl;

import com.viettel.admin.models.Organization;
import com.viettel.admin.models.Position;
import com.viettel.admin.repositories.OrganizationRepository;
import com.viettel.admin.repositories.PositionRepository;
import com.viettel.admin.services.OrganizationService;
import com.viettel.admin.services.PositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getList() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations;
    }

    @Override
    public Organization create(Organization request) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(request, organization);
        organizationRepository.save(organization);
        return request;
    }

    @Override
    public Organization update(Organization request) {
        Organization organization = organizationRepository.getById(request.getId());
        organization.setOrganizationCode(request.getOrganizationCode());
        organization.setOrganizationName(request.getOrganizationName());
        organization.setOrganizationFullName(request.getOrganizationFullName());
        organization.setOrganizationShortName(request.getOrganizationShortName());
        organization.setOrganizationTradingName(request.getOrganizationTradingName());
        organization.setPersonRepresentative(request.getPersonRepresentative());
        organization.setPositionId(request.getPositionId());
        organizationRepository.save(organization);
        return request;
    }

    @Override
    public Boolean delete(Long id) {
        organizationRepository.deleteById(id);
        return true;
    }
}
