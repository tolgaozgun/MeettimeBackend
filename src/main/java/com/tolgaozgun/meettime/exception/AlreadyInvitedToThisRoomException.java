package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class AlreadyInvitedToThisRoomException extends BaseException {


    public AlreadyInvitedToThisRoomException() {
        super("User is already invited to this room!", HttpStatus.BAD_REQUEST);
    }

    public AlreadyInvitedToThisRoomException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public AlreadyInvitedToThisRoomException(HttpStatus httpStatus) {
        super("User is already invited to this room!", httpStatus);
    }

}
