package com.tecqasr.notes.app.tecqasrnotes.exceptions;

public class ApiExceptions extends RuntimeException{
    public ApiExceptions(String message){
        super(message);
    }

    public ApiExceptions() {
        super();
    }
}
