package com.viettel.admin.request.filter;

import com.viettel.admin.core.enums.LanguageType;
import lombok.Data;

@Data
public class BaseFilter {
    protected String searchText;

    protected Integer pageNumber;

    protected Integer pageSize;

    protected LanguageType languageType;
}
