package com.customerapi.exceptions;

public class CustomerNotFundException extends RuntimeException{
    public CustomerNotFundException(String message) {
        super(message);
    }
}
