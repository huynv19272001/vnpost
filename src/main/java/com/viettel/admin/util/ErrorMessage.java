package com.viettel.admin.util;

public enum ErrorMessage {
    INCORRECT_USERNAME_PASSWORD("400","The Username or Password is Incorrect"),

    USER_MUST_BE_IN_BROWSING_STATUS("400","USER MUST BE IN BROWSING STATUS","người dùng phải ở trạng thái đang đợi duyệt","USER MUST BE IN BROWSING STATUS"),
    NO_USER_FOUND_WITH_USERNAME("400","no user found with userName"),
    CUSTOMER_CODE_NO_HOLLOW("400","customer code no hollow","customerCode không được rỗng","customer code no hollow"),
    DOES_NOT_EXIST_CUSTOMER("400","does not exist customer","không tồn tại người dùng để duyệt","does not exist customer"),
    INTERNAL_SERVER_ERROR("500","internal server error","hệ thống đang bị lỗi","internal server error"),
    CUS_CODE_ALREADY_EXIST("400","cusCode already exist","customerCode đã tồn tại","cusCode already exist"),
    CMT_ALREADY_EXIST("500","cmt already exist","Chứng minh thư đã tồn tại trên hệ thống","cmt already exist"),
    MOBILE_ALREADY_EXIST("500","mobile already exist","số điện thoại đã tồn tại","mobile already exist"),
    PERMISION("403","permision");


    private String code;
    private String message;
    private String messageVi;
    private String messageEn;

    private ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private ErrorMessage(String code, String message, String messageVi, String messageEn) {
        this.code = code;
        this.message = message;
        this.messageVi = messageVi;
        this.messageEn = messageEn;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageVi() {
        return messageVi;
    }

    public String getMessageEn() {
        return messageEn;
    }
}
