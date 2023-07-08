package com.project.Bookmyshow.ExceptionHandling;

public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String s) {
        super(s);
    }
}
