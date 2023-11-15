package com.example.projectaandmsystem.global.jwt.exception;

import com.example.projectaandmsystem.global.error.exception.BusinessException;
import com.example.projectaandmsystem.global.error.exception.ErrorCode;

public class NotExistsAuthException extends BusinessException {
    public NotExistsAuthException() {
        super(ErrorCode.NOT_EXISTS_AUTH);
    }
}
