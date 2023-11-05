package com.viettel.admin.request.filter;

import com.viettel.admin.request.filter.BaseFilter;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PartnerFilter extends BaseFilter {

    private String employeeCode;

    private List<Integer> dealerClassificationIdList;

    private List<Integer> categoryServiceIdList;

    private Map<String, String> sortBy = new HashMap<>();
}
