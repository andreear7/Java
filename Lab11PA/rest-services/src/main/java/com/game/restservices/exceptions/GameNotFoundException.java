package com.game.restservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Game not found!")
public class GameNotFoundException extends RuntimeException  {

        public GameNotFoundException(String id) {
            super("Could not find game " + id);
        }

}
