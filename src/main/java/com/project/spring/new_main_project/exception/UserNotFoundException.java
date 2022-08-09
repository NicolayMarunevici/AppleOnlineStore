package com.project.spring.new_main_project.exception;

public class UserNotFoundException extends Exception{

    String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }
}
