package com.pragma.powerup.domain.spi.token;

public interface IToken {
    String getBearerToken();
    String getEmail(String token);
    Long getUserAuthenticatedId(String token);
    String getUserAuthenticatedRol(String token);
}
