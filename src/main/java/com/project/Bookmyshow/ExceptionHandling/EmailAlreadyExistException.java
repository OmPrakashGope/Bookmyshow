package com.project.Bookmyshow.ExceptionHandling;

public class EmailAlreadyExistException extends Exception{
    public EmailAlreadyExistException(String emailIdAlreadyExist)
    {
        super(emailIdAlreadyExist);
    }
}
