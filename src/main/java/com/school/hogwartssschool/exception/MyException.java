package com.school.hogwartssschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyException extends RuntimeException{
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
