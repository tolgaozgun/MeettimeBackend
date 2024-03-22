package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class IllegalRoomCreateArgumentException extends BaseException {


    public IllegalRoomCreateArgumentException() {
        super("Illegal room create argument!", HttpStatus.BAD_REQUEST);
    }

    public IllegalRoomCreateArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public IllegalRoomCreateArgumentException(HttpStatus httpStatus) {
        super("Illegal room create argument!", httpStatus);
    }

}
