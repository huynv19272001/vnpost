package com.viettel.admin.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BaseSearchRequest {

    private String searchText;

    private Integer pageNumber;

    private Integer pageSize;


}
