package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class RoomInvitationDeclinedException extends BaseException {


    public RoomInvitationDeclinedException() {
        super("User has declined the room invitation!", HttpStatus.BAD_REQUEST);
    }

    public RoomInvitationDeclinedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public RoomInvitationDeclinedException(HttpStatus httpStatus) {
        super("User has declined the room invitation!", httpStatus);
    }

}
