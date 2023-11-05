package com.viettel.admin.models;

import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "partner_serviece")
public class PartnerService {
    private Long partnerId;
    private Long category_service_id;
}
