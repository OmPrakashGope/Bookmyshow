package com.project.Bookmyshow.ExceptionHandling;

public class ShowNotFoundException extends Exception{
    public ShowNotFoundException(String givenShowDoesNotExist) {
        super(givenShowDoesNotExist);
    }
}
