package com.university.Sustainable_Living_education.exception;
public class ErrorResponse {

    private String message;

    // Constructor
    public ErrorResponse(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}