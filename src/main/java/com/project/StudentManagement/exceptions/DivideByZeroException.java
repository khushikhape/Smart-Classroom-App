package com.project.StudentManagement.exceptions;

public class DivideByZeroException extends RuntimeException{

    public DivideByZeroException(String msg) {
        super(msg);
    }
}