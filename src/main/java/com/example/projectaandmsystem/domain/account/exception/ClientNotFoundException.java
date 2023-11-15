package com.example.projectaandmsystem.domain.account.exception;

import com.example.projectaandmsystem.global.error.exception.BusinessException;
import com.example.projectaandmsystem.global.error.exception.ErrorCode;

public class ClientNotFoundException extends BusinessException {
    public ClientNotFoundException() {
        super(ErrorCode.CLIENT_NOT_FOUND);
    }
}
