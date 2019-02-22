package com.fiaschetti.bankdemo.exception;

public class BankRequestException extends Exception {

    public BankRequestException() {
    }

    public BankRequestException(String message) {
        super(message);
    }
}
