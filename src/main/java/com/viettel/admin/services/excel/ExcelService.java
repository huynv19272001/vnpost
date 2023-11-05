package com.viettel.admin.services.excel;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    void importExcel(MultipartFile file);
}
