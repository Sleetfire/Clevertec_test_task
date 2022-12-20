package com.barkovsky.check_runner.exception;

public class CommandLineArgumentException extends NumberFormatException {

    public CommandLineArgumentException() {
        super();
    }

    public CommandLineArgumentException(String s) {
        super(s);
    }
}
