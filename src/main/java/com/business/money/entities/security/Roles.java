package com.business.money.entities.security;

import org.springframework.stereotype.Component;

@Component("Roles")
public final class Roles {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
}
