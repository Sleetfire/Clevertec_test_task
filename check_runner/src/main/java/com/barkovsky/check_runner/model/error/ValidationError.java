package com.barkovsky.check_runner.model.error;

public class ValidationError {

    private String argument;

    public ValidationError(String argument) {
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }
}
