package com.example.projectaandmsystem.domain.team.exception;

import com.example.projectaandmsystem.global.error.exception.BusinessException;
import com.example.projectaandmsystem.global.error.exception.ErrorCode;

public class AlreadyInvitedMemberException extends BusinessException {
    public AlreadyInvitedMemberException() {
        super(ErrorCode.ALREADY_INVITED_MEMBER);
    }
}
