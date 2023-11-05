package com.viettel.admin.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.stream.Stream;

public enum CustomerStatus {
    ENTERPRISE(1),
    INDIVIDUAL(2);

    @Getter(onMethod_ = @JsonValue)
    private final Integer value;

    CustomerStatus(Integer value) {
        this.value = value;
    }

    public static CustomerStatus fromValue(Integer value) {
        return Stream.of(CustomerStatus.values())
                .filter(targetEnum -> targetEnum.value.equals(value))
                .findFirst().orElse(null);
    }
}
