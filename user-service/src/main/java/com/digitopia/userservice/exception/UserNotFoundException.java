package com.digitopia.userservice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message, String string){
        super(message);
    }
}
