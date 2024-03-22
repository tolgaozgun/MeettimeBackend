package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class RoomInvitationNotFoundException extends BaseException {


    public RoomInvitationNotFoundException() {
        super("The room invitation is not found, it may be expired.", HttpStatus.BAD_REQUEST);
    }

    public RoomInvitationNotFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public RoomInvitationNotFoundException(HttpStatus httpStatus) {
        super("The room invitation is not found, it may be expired.", httpStatus);
    }

}
