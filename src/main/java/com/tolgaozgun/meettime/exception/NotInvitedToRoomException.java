package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class NotInvitedToRoomException extends BaseException {


    public NotInvitedToRoomException() {
        super("You are not invited to this room!", HttpStatus.BAD_REQUEST);
    }

    public NotInvitedToRoomException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public NotInvitedToRoomException(HttpStatus httpStatus) {
        super("You are not invited to this room!", httpStatus);
    }

}
