package com.barkovsky.check_runner.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleResponseError {
    @JsonProperty("logref")
    private String logRef;
    @JsonProperty("message")
    private String message;

    public SingleResponseError(String logRef) {
        this.logRef = logRef;
        this.message = "error";
    }

    public String getLogRef() {
        return logRef;
    }

    public String getMessage() {
        return message;
    }
}
