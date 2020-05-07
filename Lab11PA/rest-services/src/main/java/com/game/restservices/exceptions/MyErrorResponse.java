package com.game.restservices.exceptions;
import java.time.LocalDateTime;

public class MyErrorResponse {
    public MyErrorResponse(String message) {
        super();
        this.message = message;

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timeStamp) {
        this.timestamp = timeStamp;
    }

    private String message;
    private LocalDateTime timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
