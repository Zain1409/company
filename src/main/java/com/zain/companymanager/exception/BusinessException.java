package com.zain.companymanager.exception;

public class BusinessException extends Exception{
    private final Integer status;
    private final String messageCode;
    private final String message;

    public BusinessException(Integer status, String messageCode) {
        this.status = status;
        this.messageCode = messageCode;
        this.message = null;
    }

    public BusinessException(Integer status, String messageCode, String message) {
        this.status = status;
        this.messageCode = messageCode;
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getMessageCode() {
        return this.messageCode;
    }

    public String getMessage() {
        return this.message;
    }
}
