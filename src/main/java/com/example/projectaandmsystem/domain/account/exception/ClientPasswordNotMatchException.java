package com.example.projectaandmsystem.domain.account.exception;

import com.example.projectaandmsystem.global.error.exception.BusinessException;
import com.example.projectaandmsystem.global.error.exception.ErrorCode;

public class ClientPasswordNotMatchException extends BusinessException {
    public ClientPasswordNotMatchException() {
        super(ErrorCode.CLIENT_PASSWORD_NOT_MATCH);
    }
}
