package com.viettel.admin.request;

import lombok.Data;

@Data
public class CustomerSearch {
    public Integer pageIndex;
    public Integer pageSize;
    public String searchText;
    public Integer customerType;
    public Integer status;
}
