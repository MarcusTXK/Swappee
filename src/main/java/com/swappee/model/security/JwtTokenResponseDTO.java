package com.swappee.model.security;

import java.io.Serializable;

/**
 * the POJO for the Response json
 */
public class JwtTokenResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String token;

    public JwtTokenResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    @Override
    public String toString() {
        return "JwtTokenResponseDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}