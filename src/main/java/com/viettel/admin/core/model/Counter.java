package com.viettel.admin.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counter {

  private Long totalRecord;

  private Integer totalPage;

  private Integer pageNumber;

  private Integer pageSize;

  public Counter(Long totalRecord) {
    this.totalRecord = totalRecord;
  }
}
