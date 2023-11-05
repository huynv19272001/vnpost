package com.viettel.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NGMessages implements Serializable {
    private Long id;
    private String msgCode;
    private String ContentVi;
    private String ContentEn;
    private String status;
    private LocalDateTime localDateTime;

    @Override
    public String toString() {
        return "NGMessages{" +
                "id=" + id +
                ", msgCode='" + msgCode + '\'' +
                ", ContentVi='" + ContentVi + '\'' +
                ", ContentEn='" + ContentEn + '\'' +
                ", status='" + status + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
