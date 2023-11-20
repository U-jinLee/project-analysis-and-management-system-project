package com.example.projectaandmsystem.domain.team.exception;

import com.example.projectaandmsystem.global.error.exception.BusinessException;
import com.example.projectaandmsystem.global.error.exception.ErrorCode;

public class TicketExistException extends BusinessException {
    public TicketExistException() {
        super(ErrorCode.TICKET_EXIST);
    }
}
