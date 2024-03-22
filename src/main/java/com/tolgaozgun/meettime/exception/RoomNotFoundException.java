package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends BaseException {


    public RoomNotFoundException() {
        super("Room not found!", HttpStatus.BAD_REQUEST);
    }

    public RoomNotFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public RoomNotFoundException(HttpStatus httpStatus) {
        super("Room not found!", httpStatus);
    }

}
