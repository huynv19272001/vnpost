package com.viettel.admin.services.excel;

import org.springframework.web.multipart.MultipartFile;

public interface PartnerExcelService {
    void importExcel(MultipartFile file);
}
