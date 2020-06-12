package com.example.springredditclone.exception;

/**
 * Общее исключение для приложения
 */
public class SpringRedditException extends RuntimeException {

    public SpringRedditException(String message) {
        super(message);
    }
}
