package com.swappee.model.security;

import java.io.Serializable;

/**
 * the POJO for the Request json of username and password
 */
public class JwtTokenRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtTokenRequestDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
