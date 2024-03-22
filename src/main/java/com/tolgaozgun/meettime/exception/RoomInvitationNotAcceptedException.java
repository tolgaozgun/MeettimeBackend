package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class RoomInvitationNotAcceptedException extends BaseException {


    public RoomInvitationNotAcceptedException() {
        super("You have not accepted the room invitation!", HttpStatus.BAD_REQUEST);
    }

    public RoomInvitationNotAcceptedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public RoomInvitationNotAcceptedException(HttpStatus httpStatus) {
        super("You have not accepted the room invitation!", httpStatus);
    }

}
