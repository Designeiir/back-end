package com.example.backend.exception;

import lombok.Data;

/**
 *
 * */
@Data
public class OSException extends RuntimeException {

    private int errorCode;
    private String errorMsg;

    public OSException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public OSException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public OSException(String errorMsg) {
        super(errorMsg);
        this.errorCode = 500;
        this.errorMsg = errorMsg;
    }

    public OSException(OSExceptionEnum osExceptionEnum) {
        super(osExceptionEnum.getErrorMsg());
        this.errorCode = osExceptionEnum.getErrorCode();
        this.errorMsg = osExceptionEnum.getErrorMsg();
    }
}
