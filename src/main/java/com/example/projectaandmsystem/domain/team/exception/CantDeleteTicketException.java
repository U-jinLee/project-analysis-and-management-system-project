package com.example.projectaandmsystem.domain.team.exception;

import com.example.projectaandmsystem.global.error.exception.BusinessException;
import com.example.projectaandmsystem.global.error.exception.ErrorCode;

public class CantDeleteTicketException extends BusinessException {

    public CantDeleteTicketException() {
        super(ErrorCode.CANT_DELETE_TICKET);
    }

}
