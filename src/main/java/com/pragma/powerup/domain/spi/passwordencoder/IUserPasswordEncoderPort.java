package com.pragma.powerup.domain.spi.passwordencoder;

public interface IUserPasswordEncoderPort {
    String encode(String clave);
}
