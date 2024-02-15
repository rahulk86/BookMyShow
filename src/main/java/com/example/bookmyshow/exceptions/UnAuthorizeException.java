package com.example.bookmyshow.exceptions;

public class UnAuthorizeException extends Exception{

    public UnAuthorizeException(Long userId){
        super("user not found with given id : "+userId);
    }
}
