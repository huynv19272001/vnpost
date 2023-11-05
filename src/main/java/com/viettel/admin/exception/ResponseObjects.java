package com.viettel.admin.exception;

import com.viettel.admin.util.ErrorMessage;
import lombok.Data;

@Data
public class ResponseObjects<T>{
    private String code;
    private String message;
    private String messageVi;
    private String messageEn;
    private  T data;

    public ResponseObjects(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseObjects(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> ResponseObjects<T> error(ErrorMessage errorMessage){
        ResponseObjects<T> object = new ResponseObjects<>(errorMessage.getCode(),errorMessage.getMessage());
        object.setMessageVi(errorMessage.getMessageVi());
        object.setMessageEn(errorMessage.getMessageEn());
        return object;
    }
    public static <T> ResponseObjects<T> error(String code, String message){
        return new ResponseObjects<>(code,message);
    }
}
