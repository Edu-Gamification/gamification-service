package com.business.money.exception.exceptions;

public class EventTypeNotFoundException extends Exception {
    public EventTypeNotFoundException() {
        super("Тип события не найден");
    }
}
