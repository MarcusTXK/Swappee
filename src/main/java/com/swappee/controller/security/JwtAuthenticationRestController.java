package com.swappee.controller.security;

import com.google.common.base.Preconditions;
import com.swappee.model.security.JwtTokenRequestDTO;
import com.swappee.model.security.JwtTokenResponseDTO;
import com.swappee.model.user.UserDTO;
import com.swappee.utils.exception.AuthenticationException;
import com.swappee.utils.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/login")
public class JwtAuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponseDTO> createAuthenticationToken(@RequestBody JwtTokenRequestDTO authenticationRequest) {
        //check if username and password is correct
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        //load user details and creates the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtTokenResponseDTO(token));
    }

    //Checks if it is valid
    //If valid, check if the user details and expiration date are matching and create the token
    //Else return a bad request
    @GetMapping("/refresh")
    public ResponseEntity<JwtTokenResponseDTO> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDTO user = (UserDTO) userDetailsService.loadUserByUsername(username);
        Preconditions.checkNotNull(user);
        if (Boolean.TRUE.equals(jwtTokenUtil.canTokenBeRefreshed(token))) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponseDTO(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }


    private void authenticate(String username, String password) {
        try {
            Preconditions.checkNotNull(username);
            Preconditions.checkNotNull(password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}