package com.viettel.admin.exception;

import com.viettel.admin.util.ErrorMessage;
import lombok.Data;

@Data
public class VTPostException extends RuntimeException{
    private String code;
    private String fullCode;
    private String message;
    private Object data;
    private ErrorMessage errorMessage;

    public VTPostException(String code, String prefix, String message, Object data) {
        this.code = code;
        this.fullCode = prefix != null && prefix.isEmpty() ? prefix + "_" + code:code;
        this.message = message;
        this.data = data;
    }
    public VTPostException(ErrorMessage errorMessage, String prefix, Object data) {
        this.code = errorMessage.getCode();
        this.fullCode = prefix != null && prefix.isEmpty() ? prefix + "_" + code:code;
        this.message = errorMessage.getMessage();
        this.data = data;
    }
    public VTPostException(ErrorMessage errorMessage, String prefix) {
        this.code = errorMessage.getCode();
        this.fullCode = prefix != null && prefix.isEmpty() ? prefix + "_" + code:code;
        this.message = errorMessage.getMessage();
        this.errorMessage = errorMessage;
    }
}
