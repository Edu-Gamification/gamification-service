package com.business.money.exception.exceptions;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException() {
        super("Роль не найдена");
    }
}
