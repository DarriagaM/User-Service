package com.pragma.powerup.infrastructure.out.jpa.passwordencoder;

import com.pragma.powerup.domain.spi.passwordencoder.IUserPasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter implements IUserPasswordEncoderPort {
    @Override
    public String encode(String clave) {
        return BCrypt.hashpw(clave, BCrypt.gensalt());
    }
}
