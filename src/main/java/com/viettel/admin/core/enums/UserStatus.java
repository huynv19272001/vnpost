package com.viettel.admin.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.stream.Stream;

public enum UserStatus {

  ACTIVE(1),
  DEACTIVATE(2),

  ALL(3);

  @Getter(onMethod_ = @JsonValue)
  private final Integer value;

//  public Integer getValue(){
//    return this.value;
//  }
//
//
  UserStatus(Integer value) {
    this.value = value;
  }

  public static UserStatus fromValue(Integer value) {
    return Stream.of(UserStatus.values())
        .filter(targetEnum -> targetEnum.value.equals(value))
        .findFirst().orElse(null);
  }

}
