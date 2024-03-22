package com.tolgaozgun.meettime.exception;

import org.springframework.http.HttpStatus;

public class IllegalVoteArgumentException extends BaseException {


    public IllegalVoteArgumentException() {
        super("Illegal vote argument!", HttpStatus.BAD_REQUEST);
    }

    public IllegalVoteArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public IllegalVoteArgumentException(HttpStatus httpStatus) {
        super("Illegal vote argument!", httpStatus);
    }

}
