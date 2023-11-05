package com.viettel.admin.services;

import com.viettel.admin.models.Organization;
import com.viettel.admin.models.Position;

import java.util.List;


public interface OrganizationService {

    List<Organization> getList();

    Organization create(Organization request);

    Organization update(Organization request);

    Boolean delete(Long id);

}
