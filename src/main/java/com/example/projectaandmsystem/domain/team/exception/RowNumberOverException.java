package com.example.projectaandmsystem.domain.team.exception;

import com.example.projectaandmsystem.global.error.exception.BusinessException;
import com.example.projectaandmsystem.global.error.exception.ErrorCode;

public class RowNumberOverException extends BusinessException {
    public RowNumberOverException() {
        super(ErrorCode.ROW_NUMBER_OVER);
    }
}
